package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/meeting-room")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    public MeetingRoomController(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("meetingRooms", meetingRoomService.findAll());

        return "meeting-room/list";
    }

    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("meetingRoom", meetingRoomService.findById(id));

        return "meeting-room/display";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("meetingRoom", new MeetingRoom());

        return "meeting-room/meeting-room-form";
    }

    @PostMapping("/processAddForm")
    public String processAddForm(@ModelAttribute MeetingRoom meetingRoom, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("meetingRoom", meetingRoom);
            return "meeting-room/meeting-room-form";
        }

        MeetingRoom savedMeetingRoom = meetingRoomService.save(meetingRoom);

        return "meeting-room/added-confirmation";
    }
}
