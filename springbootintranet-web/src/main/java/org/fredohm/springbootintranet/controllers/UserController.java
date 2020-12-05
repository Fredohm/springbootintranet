package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.domain.User;
import org.fredohm.springbootintranet.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/list", "/list.html"})
    public String list(Model model) {

        model.addAttribute("users", userService.findAll());

        return "user/list";
    }

    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userService.findById(id));

        return "user/display";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        User user = new User();

        model.addAttribute("user", user);

        return "user/user-form";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("user", userService.findById(id));

        return "user/user-form";
    }

    @PostMapping("/processUserForm")
    public String saveOrUpdateUser(@ModelAttribute User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "user/user-form";
        }

        User savedUser = userService.save(user);

        return "redirect:/user/display/" + savedUser.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        userService.deleteById(id);

        return "redirect:/user/list";
    }
}
