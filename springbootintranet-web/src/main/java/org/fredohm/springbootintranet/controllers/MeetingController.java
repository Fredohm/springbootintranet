package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    private final MeetingRoomService meetingRoomService;

    public MeetingController(MeetingService meetingService, MeetingRoomService meetingRoomService) {
        this.meetingService = meetingService;
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping({"/list", "/list.html"})
    public String list(Model model) {

        model.addAttribute("meetings", meetingService.findAll());

        return "meeting/list";
    }

    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("meeting", meetingService.findById(id));

        return "meeting/display";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        Meeting meeting = new Meeting();

        model.addAttribute("meeting", meeting);
        model.addAttribute("meetingRoomList", meetingRoomService.findAll());

        return "meeting/add-form";
    }

    @PostMapping("/processAddForm")
    public String processAddForm(@ModelAttribute Meeting meeting, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            model.addAttribute("meeting", meeting);
            return "meeting/add-form";
        }

        meetingService.save(meeting);

        return "meeting/added-confirmation";
    }
}
