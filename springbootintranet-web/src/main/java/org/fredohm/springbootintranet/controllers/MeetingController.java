package org.fredohm.springbootintranet.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.config.permissions.meeting.CreateMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.DeleteMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.ReadMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.UpdateMeeting;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController extends ErrorController {

    private final MeetingService meetingService;
    private final MeetingRoomService meetingRoomService;

    @ReadMeeting
    @GetMapping({"/list", "/list.html"})
    public String list(Model model) {

        model.addAttribute("meetings", meetingService.findByDateAfterOrderByDateAsc(LocalDate.now()));

        return "meeting/list";
    }

    @ReadMeeting
    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("meeting", meetingService.findById(id));

        return "meeting/display";
    }

    @CreateMeeting
    @GetMapping("/add")
    public String addForm(Model model) {

        Meeting meeting = new Meeting();

        model.addAttribute("meeting", meeting);
        model.addAttribute("meetingRoomList", meetingRoomService.findAllByAvailableIsTrueOrderByNameAsc());

        return "meeting/meeting-form";
    }

    @UpdateMeeting
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("meeting", meetingService.findById(id));
        model.addAttribute("meetingRoomList", meetingRoomService.findAllByAvailableIsTrueOrderByNameAsc());

        return "meeting/meeting-form";
    }

    @CreateMeeting
    @UpdateMeeting
    @PostMapping("/processMeetingForm")
    public String saveOrUpdateMeeting(@Valid @ModelAttribute Meeting meeting,
                                      BindingResult result, @RequestParam("meetingRoom.id") Long id, Model model) {


        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            model.addAttribute("meetingRoomList", meetingRoomService.findAll());

            return "meeting/meeting-form";
        }

        meetingRoomService.findById(id).getMeetings().add(meeting);

        meeting.setMeetingRoom(meetingRoomService.findById(id));

        Meeting savedMeeting = meetingService.save(meeting);

        return "redirect:/meeting/display/" + savedMeeting.getId();
    }

    @DeleteMeeting
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        meetingService.deleteById(id);

        return "redirect:/meeting/list";
    }
}
