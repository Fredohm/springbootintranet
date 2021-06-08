package org.fredohm.springbootintranet.controllers.api.v1;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.config.permissions.meeting.CreateMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.DeleteMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.ReadMeeting;
import org.fredohm.springbootintranet.config.permissions.meeting.UpdateMeeting;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.services.api.v1.MeetingRestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(MeetingRestController.BASE_URL)
public class MeetingRestController {

    public static final String BASE_URL = "/api/v1/meetings";

    public static final Integer DEFAULT_PAGE_NUMBER = 0;
    public static final Integer DEFAULT_PAGE_SIZE =25;

    private final MeetingRestService meetingRestService;

    @ReadMeeting
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<MeetingDTO> getListOfMeeting() {
        return meetingRestService.getAllMeetings();
    }



    @ReadMeeting
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingDTO getMeetingById(@PathVariable Long id) {
        return meetingRestService.getMeetingById(id);
    }

    @CreateMeeting
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MeetingDTO createNewMeeting(@RequestBody MeetingDTO meetingDTO) {
        return meetingRestService.createNewMeeting(meetingDTO);
    }

    @UpdateMeeting
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingDTO updateMeeting(@PathVariable Long id, @RequestBody MeetingDTO meetingDTO) {
        return meetingRestService.updateMeeting(id, meetingDTO);
    }

    @UpdateMeeting
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingDTO patchMeeting(@PathVariable Long id, @RequestBody MeetingDTO meetingDTO) {
        return meetingRestService.patchMeeting(id, meetingDTO);
    }

    @DeleteMeeting
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMeetingRoom(@PathVariable Long id) {
        meetingRestService.deleteMeetingById(id);
    }
}
