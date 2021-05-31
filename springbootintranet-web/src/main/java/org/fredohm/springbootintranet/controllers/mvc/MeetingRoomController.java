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
import org.fredohm.springbootintranet.services.api.v1.MeetingRoomRestService;
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

    private final MeetingRoomRestService meetingRoomRestService;

    @ReadMeetingRoom
    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("meetingRoomsDTO", meetingRoomRestService.getAllMeetingRooms());

        return "meeting-room/list";
    }

    @ReadMeetingRoom
    @GetMapping("/display/{id}")
    public String display(Model model, @PathVariable("id") Long id) {

        model.addAttribute("meetingRoomDTO", meetingRoomRestService.getMeetingRoomById(id));

        return "meeting-room/display";
    }

    @CreateMeetingRoom
    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("meetingRoomDTO", new MeetingRoom());

        return "meeting-room/meeting-room-form";
    }

    @UpdateMeetingRoom
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("meetingRoomDTO", meetingRoomRestService.getMeetingRoomById(id));

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

        if (meetingRoomDTO.getId() == null) {
            MeetingRoomDTO newMeetingRoomDTO = meetingRoomRestService.createNewMeetingRoom(meetingRoomDTO);
            return "redirect:/meeting-room/display/" + newMeetingRoomDTO.getId();
        } else {
            meetingRoomRestService.patchMeetingRoom(meetingRoomDTO.getId(), meetingRoomDTO);
            return "redirect:/meeting-room/display/" + meetingRoomDTO.getId();
        }
    }

    @DeleteMeetingRoom
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        meetingRoomRestService.deleteMeetingRoomById(id);

        return "redirect:/meeting-room/list";
    }
}
