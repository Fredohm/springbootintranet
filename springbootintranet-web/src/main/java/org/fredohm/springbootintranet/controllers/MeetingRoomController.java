package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meeting-room")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    public MeetingRoomController(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping({"/list", "/list.html"})
    public String list(Model model) {

        model.addAttribute("meetingRooms", meetingRoomService.findAll());

        return "meeting-room/list";
    }

    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("meetingRoom", meetingRoomService.findById(id));

        return "meeting-room/display";
    }
}
