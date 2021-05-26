package org.fredohm.springbootintranet.controllers.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.config.permissions.meeting.CreateMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.DeleteMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.ReadMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.UpdateMeeting;
import org.fredohm.springbootintranet.controllers.ErrorController;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.services.api.v1.MeetingRestService;
import org.fredohm.springbootintranet.services.sdjpa.MeetingRoomSDJpaService;
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

    private final MeetingRestService meetingRestService;
    private final MeetingRoomSDJpaService meetingRoomService;

    @ReadMeeting
    @GetMapping({"/list", "/list.html"})
    public String list(Model model) {

        model.addAttribute("meetings", meetingRestService.getMeetingByDateAfterOrderByDateAsc(LocalDate.now()));

        return "meeting/list";
    }

    @ReadMeeting
    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("meetingDTO", meetingRestService.getMeetingById(id));

        return "meeting/display";
    }

    @CreateMeeting
    @GetMapping("/add")
    public String addForm(Model model) {

        MeetingDTO meetingDTO = new MeetingDTO();

        model.addAttribute("meetingDTO", meetingDTO);
        model.addAttribute("meetingRoomList", meetingRoomService.findAllByAvailableIsTrueOrderByNameAsc());

        return "meeting/meeting-form";
    }

    @UpdateMeeting
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("meetingDTO", meetingRestService.getMeetingById(id));
        model.addAttribute("meetingRoomList", meetingRoomService.findAllByAvailableIsTrueOrderByNameAsc());

        return "meeting/meeting-form";
    }

    @CreateMeeting
    @UpdateMeeting
    @PostMapping("/processMeetingForm")
    public String saveOrUpdateMeeting(@Valid @ModelAttribute MeetingDTO meetingDTO,
                                      BindingResult result, @RequestParam("meetingRoomDTO.id") Long meetingRoomId, Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            model.addAttribute("meetingRoomList", meetingRoomService.findAllByAvailableIsTrueOrderByNameAsc());

            return "meeting/meeting-form";
        }

        meetingRoomService.findById(meetingRoomId).getMeetings().add(meetingDTO);

        meetingDTO.setMeetingRoomDTO(meetingRoomService.findById(meetingRoomId));
        System.out.println(meetingDTO);
        if (meetingDTO.getId() == null) {
            MeetingDTO newMeetingDTO = meetingRestService.createNewMeeting(meetingDTO);
            return "redirect:/meeting/display/" + newMeetingDTO.getId();
        } else {
            meetingRestService.patchMeeting(meetingDTO.getId(), meetingDTO);
            return "redirect:/meeting/display/" + meetingDTO.getId();
        }



    }

    @DeleteMeeting
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        meetingRestService.deleteMeetingById(id);

        return "redirect:/meeting/list";
    }
}
