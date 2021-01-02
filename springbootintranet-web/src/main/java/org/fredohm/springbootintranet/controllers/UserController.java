package org.fredohm.springbootintranet.controllers;

import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.AppUser;
import org.fredohm.springbootintranet.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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

        AppUser user = new AppUser();

        model.addAttribute("user", user);

        return "user/user-form";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("user", userService.findById(id));

        return "user/user-form";
    }

    @PostMapping("/processUserForm")
    public String saveOrUpdateUser(@Valid @ModelAttribute AppUser user, BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return "user/user-form";
        }

        AppUser savedUser = userService.save(user);

        return "redirect:/user/display/" + savedUser.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        userService.deleteById(id);

        return "redirect:/user/list";
    }
}
