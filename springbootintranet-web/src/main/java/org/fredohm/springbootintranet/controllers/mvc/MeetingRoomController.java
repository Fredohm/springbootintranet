package org.fredohm.springbootintranet.controllers.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.config.permissions.meetingRoom.CreateMeetingRoom;
import org.fredohm.springbootintranet.config.permissions.meetingRoom.DeleteMeetingRoom;
import org.fredohm.springbootintranet.config.permissions.meetingRoom.ReadMeetingRoom;
import org.fredohm.springbootintranet.config.permissions.meetingRoom.UpdateMeetingRoom;
import org.fredohm.springbootintranet.controllers.ErrorController;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.services.sdjpa.MeetingRoomSDJpaService;
import org.fredohm.springbootintranet.services.sdjpa.MeetingSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting-room")
public class MeetingRoomController extends ErrorController {

    private final MeetingRoomSDJpaService meetingRoomService;
    private final MeetingSDJpaService meetingService;


    @ReadMeetingRoom
    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("meetingRooms", meetingRoomService.findAllByOrderByNameAsc());

        return "meeting-room/list";
    }

    @ReadMeetingRoom
    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("meetingRoom", meetingRoomService.findById(id));

        return "meeting-room/display";
    }

    @CreateMeetingRoom
    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("meetingRoom", new MeetingRoom());

        return "meeting-room/meeting-room-form";
    }

    @UpdateMeetingRoom
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("meetingRoom", meetingRoomService.findById(id));

        return "meeting-room/meeting-room-form";
    }

    @CreateMeetingRoom
    @UpdateMeetingRoom
    @PostMapping("/processMeetingRoomForm")
    public String saveOrUpdateMeetingRoom(@Valid @ModelAttribute MeetingRoomDTO meetingRoomDTO, BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return "meeting-room/meeting-room-form";
        }

        MeetingRoomDTO savedMeetingRoom = meetingRoomService.save(meetingRoomDTO);

        return "redirect:/meeting-room/display/" + savedMeetingRoom.getId();
    }

    @DeleteMeetingRoom
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        meetingRoomService.deleteById(id);

        return "redirect:/meeting-room/list";
    }
}
