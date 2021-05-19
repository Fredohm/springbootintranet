package org.fredohm.springbootintranet.controllers.api.v1;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.config.permissions.meeting.CreateMeeting;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.model.MeetingListDTO;
import org.fredohm.springbootintranet.services.api.v1.MeetingRestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(MeetingRestController.BASE_URL)
public class MeetingRestController {

    public static final String BASE_URL = "/api/v1/meetings";
    private final MeetingRestService meetingRestService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MeetingListDTO getListOfMeeting() {
        return new MeetingListDTO((meetingRestService.getAllMeetings()));
    }

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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingDTO updateMeeting(@PathVariable Long id, @RequestBody MeetingDTO meetingDTO) {
        return meetingRestService.saveMeetingByDTO(id, meetingDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingDTO patchMeeting(@PathVariable Long id, @RequestBody MeetingDTO meetingDTO) {
        return meetingRestService.patchMeeting(id, meetingDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMeetingRoom(@PathVariable Long id) {
        meetingRestService.deleteMeetingById(id);
    }
}
