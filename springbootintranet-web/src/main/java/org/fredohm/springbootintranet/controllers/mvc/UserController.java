package org.fredohm.springbootintranet.controllers.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.config.permissions.user.CreateUser;
import org.fredohm.springbootintranet.config.permissions.user.DeleteUser;
import org.fredohm.springbootintranet.config.permissions.user.ReadUser;
import org.fredohm.springbootintranet.config.permissions.user.UpdateUser;
import org.fredohm.springbootintranet.controllers.ErrorController;
import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.model.UserDTO;
import org.fredohm.springbootintranet.services.api.v1.UserRestService;
import org.fredohm.springbootintranet.services.sdjpa.security.RoleService;
import org.fredohm.springbootintranet.services.sdjpa.security.UserService;
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

    private final UserService userService;
    private final UserRestService userRestService;
    private final RoleService roleService;

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

        userService.findById(id);
        List<Role> roles = roleService.findAll();

        UserDTO userToUpdate = UserDTO.builder()
                .id(id)
                .username(userService.findById(id).getUsername())
                .firstName(userService.findById(id).getFirstName())
                .lastName(userService.findById(id).getLastName())
                .email(userService.findById(id).getEmail())
                .build();

        model.addAttribute("userDTO", userToUpdate);
        model.addAttribute("roles", roles);

        return "user/user-form";
    }

    @CreateUser
    @UpdateUser
    @PostMapping("/processUserForm")
    public String saveOrUpdateUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, @RequestParam("role.id") Long id, Model model) {

        List<Role> roles = roleService.findAll();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute("userDTO", userDTO);
            model.addAttribute("roles", roles);

            return "user/user-form";
        }

        User userToSave = User.builder()
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(roleService.findById(id))
                .build();
        userService.save(userToSave);

        return "redirect:/user/display/" + userToSave.getId();
    }

    @DeleteUser
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        userService.deleteById(id);

        return "redirect:/user/list";
    }
}
