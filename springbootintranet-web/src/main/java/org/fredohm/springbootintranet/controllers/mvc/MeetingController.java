package org.fredohm.springbootintranet.controllers.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.config.permissions.meeting.CreateMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.DeleteMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.ReadMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.UpdateMeeting;
import org.fredohm.springbootintranet.controllers.ErrorController;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.services.sdjpa.MeetingRoomSDJpaService;
import org.fredohm.springbootintranet.services.sdjpa.MeetingSDJpaService;
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

    private final MeetingSDJpaService meetingService;
    private final MeetingRoomSDJpaService meetingRoomService;

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
    public String saveOrUpdateMeeting(@Valid @ModelAttribute MeetingDTO meetingDTO,
                                      BindingResult result, @RequestParam("meetingRoom.id") Long id, Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            model.addAttribute("meetingRoomList", meetingRoomService.findAllByAvailableIsTrueOrderByNameAsc());

            return "meeting/meeting-form";
        }

        meetingRoomService.findById(id).getMeetings().add(meetingDTO);

        meetingDTO.setMeetingRoom(meetingRoomService.findById(id));

        MeetingDTO savedMeetingDTO = meetingService.save(meetingDTO);

        return "redirect:/meeting/display/" + savedMeetingDTO.getId();
    }

    @DeleteMeeting
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        meetingService.deleteById(id);

        return "redirect:/meeting/list";
    }
}
