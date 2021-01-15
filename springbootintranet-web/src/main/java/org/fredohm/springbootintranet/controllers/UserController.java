package org.fredohm.springbootintranet.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.config.permissions.user.CreateUser;
import org.fredohm.springbootintranet.config.permissions.user.DeleteUser;
import org.fredohm.springbootintranet.config.permissions.user.ReadUser;
import org.fredohm.springbootintranet.config.permissions.user.UpdateUser;
import org.fredohm.springbootintranet.domain.security.User;
import org.fredohm.springbootintranet.services.security.UserService;
import org.fredohm.springbootintranet.user.IntranetUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ReadUser
    @GetMapping({"/list", "/list.html"})
    public String list(Model model) {

        model.addAttribute("users", userService.findAll());

        return "user/list";
    }

    @ReadUser
    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userService.findById(id));

        return "user/display";
    }

    @CreateUser
    @GetMapping("/add")
    public String addForm(Model model) {

        IntranetUser user = new IntranetUser();

        model.addAttribute("user", user);

        return "user/user-form";
    }

    @UpdateUser
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("user", userService.findById(id));

        return "user/user-form";
    }

    @CreateUser
    @UpdateUser
    @PostMapping("/processUserForm")
    public String saveOrUpdateUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            model.addAttribute("user", user);

            return "user/user-form";
        }

        User savedUser = userService.save(user);

        return "redirect:/user/display/" + savedUser.getId();
    }

    @DeleteUser
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        userService.deleteById(id);

        return "redirect:/user/list";
    }
}
