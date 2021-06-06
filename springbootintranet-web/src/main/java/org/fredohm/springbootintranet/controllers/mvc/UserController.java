package org.fredohm.springbootintranet.controllers.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.config.permissions.user.CreateUser;
import org.fredohm.springbootintranet.config.permissions.user.DeleteUser;
import org.fredohm.springbootintranet.config.permissions.user.ReadUser;
import org.fredohm.springbootintranet.config.permissions.user.UpdateUser;
import org.fredohm.springbootintranet.controllers.ErrorController;
import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.mappers.RoleMapper;
import org.fredohm.springbootintranet.mappers.UserMapper;
import org.fredohm.springbootintranet.model.UserDTO;
import org.fredohm.springbootintranet.model.UserUpdateDTO;
import org.fredohm.springbootintranet.services.api.v1.UserRestService;
import org.fredohm.springbootintranet.services.sdjpa.security.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends ErrorController {

    private final UserRestService userRestService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    private final PasswordEncoder passwordEncoder;

    @ReadUser
    @GetMapping({"/list", "/list.html"})
    public String list(Model model) {

        model.addAttribute("users", userRestService.getAllUsers());

        return "user/list";
    }

    @ReadUser
    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userRestService.getUserById(id));

        return "user/display";
    }

    @CreateUser
    @GetMapping("/add")
    public String addForm(Model model) {

        UserDTO userDTO = new UserDTO();
        List<Role> roles = roleService.findAll();

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("roles", roles);

        return "user/user-form";
    }

    @UpdateUser
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        userRestService.getUserById(id);
        List<Role> roles = roleService.findAll();

        UserDTO userToUpdate = UserDTO.builder()
                .id(id)
                .username(userRestService.getUserById(id).getUsername())
                .firstName(userRestService.getUserById(id).getFirstName())
                .lastName(userRestService.getUserById(id).getLastName())
                .email(userRestService.getUserById(id).getEmail())
                .role(userRestService.getUserById(id).getRole())
                .build();

        model.addAttribute("userDTO", userToUpdate);
        model.addAttribute("roles", roles);

        return "user/user-form";
    }

    @CreateUser
    @UpdateUser
    @PostMapping("/processSaveUserForm")
    public String saveUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, @RequestParam("role.id") Long roleId, Model model) {

        List<Role> roles = roleService.findAll();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute("userDTO", userDTO);
            model.addAttribute("roles", roles);

            return "user/user-form";
        }

        UserDTO userToSave = UserDTO.builder()
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(roleMapper.roleToRoleDto(roleService.findById(roleId)))
                .build();
        UserDTO savedUser = userRestService.createNewUser(userToSave);

        return "redirect:/user/display/" + savedUser.getId();
    }

    @UpdateUser
    @PostMapping("/processUpdateUserForm")
    public String UpdateUser(@Valid @ModelAttribute UserUpdateDTO userUpdateDTO, BindingResult result, @RequestParam("role.id") Long roleId, Model model) {

        List<Role> roles = roleService.findAll();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute("userDTO", userUpdateDTO);
            model.addAttribute("roles", roles);

            return "user/user-form";
        }
        UserDTO userDTOToSave = UserDTO.builder()
                .id(userUpdateDTO.getId())
                .username(userUpdateDTO.getUsername())
                .firstName(userUpdateDTO.getFirstName())
                .lastName(userUpdateDTO.getLastName())
                .email(userUpdateDTO.getEmail())
                .role(roleMapper.roleToRoleDto(roleService.findById(roleId)))
                .build();

        userRestService.patchUser(userDTOToSave.getId(), userDTOToSave);

        return "redirect:/user/display/" + userDTOToSave.getId();
    }

    @DeleteUser
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        userRestService.deleteUserById(id);

        return "redirect:/user/list";
    }
}
