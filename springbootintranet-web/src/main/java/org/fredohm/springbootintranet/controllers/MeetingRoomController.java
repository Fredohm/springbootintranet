package org.fredohm.springbootintranet.controllers;

import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/meeting-room")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;
    private final MeetingService meetingService;

    public MeetingRoomController(MeetingRoomService meetingRoomService, MeetingService meetingService) {
        this.meetingRoomService = meetingRoomService;
        this.meetingService = meetingService;
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

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("meetingRoom", meetingRoomService.findById(id));

        return "meeting-room/meeting-room-form";
    }

    @PostMapping("/processMeetingRoomForm")
    public String saveOrUpdateMeetingRoom(@Valid @ModelAttribute MeetingRoom meetingRoom, BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return "meeting-room/meeting-room-form";
        }

        MeetingRoom savedMeetingRoom = meetingRoomService.save(meetingRoom);

        return "redirect:/meeting-room/display/" + savedMeetingRoom.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        meetingRoomService.deleteById(id);

        return "redirect:/meeting-room/list";
    }
}
