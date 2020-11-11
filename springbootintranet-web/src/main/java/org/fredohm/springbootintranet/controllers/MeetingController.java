package org.fredohm.springbootintranet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meeting")
public class MeetingController {

    @GetMapping({"/list", "/list.html"})
    public String list() {
        return "/meeting/list";
    }
}
