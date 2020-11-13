package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping({"/list", "/list.html"})
    public String list(Model model) {

        model.addAttribute("meetings", meetingService.findAll());

        return "/meeting/list";
    }

    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("meeting", meetingService.findById(id));

        return "meeting/display";
    }
}
