package org.fredohm.springbootintranet.controllers;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        return "meeting/meeting-form";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("meeting", meetingService.findById(id));
        model.addAttribute("meetingRoomList", meetingRoomService.findAll());

        return "meeting/meeting-form";
    }

    @PostMapping("/processMeetingForm")
    public String saveOrUpdateMeeting(@Valid @ModelAttribute Meeting meeting, @RequestParam("meetingRoom.id") Long id, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            model.addAttribute("meeting", meeting);
            return "meeting/meeting-form";
        }

        MeetingRoom updateMeetingRoom = meetingRoomService.findById(id);
        updateMeetingRoom.getMeetings().add(meeting);
        //meetingRoomService.save(updateMeetingRoom);

        meeting.setMeetingRoom(meetingRoomService.findById(id));
        Meeting savedMeeting = meetingService.save(meeting);

        return "redirect:/meeting/display/" + savedMeeting.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        meetingService.deleteById(id);

        return "redirect:/meeting/list";
    }
}
