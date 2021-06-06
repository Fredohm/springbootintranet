package org.fredohm.springbootintranet.controllers.api.v1;


import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.config.permissions.meetingRoom.CreateMeetingRoom;
import org.fredohm.springbootintranet.config.permissions.meetingRoom.DeleteMeetingRoom;
import org.fredohm.springbootintranet.config.permissions.meetingRoom.ReadMeetingRoom;
import org.fredohm.springbootintranet.config.permissions.meetingRoom.UpdateMeetingRoom;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.services.api.v1.MeetingRoomRestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(MeetingRoomRestController.BASE_URL)
public class MeetingRoomRestController {

    public static final String BASE_URL = "/api/v1/meeting_rooms";
    private final MeetingRoomRestService meetingRoomRestService;

    @ReadMeetingRoom
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MeetingRoomDTO> getListOfMeetingRooms() {
        return meetingRoomRestService.getAllMeetingRooms();
    }

    @ReadMeetingRoom
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingRoomDTO getMeetingRoomById(@PathVariable Long id) {
        return meetingRoomRestService.getMeetingRoomById(id);
    }

    @CreateMeetingRoom
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MeetingRoomDTO createNewMeetingRoom(@RequestBody MeetingRoomDTO meetingRoomDTO) {
        return meetingRoomRestService.createNewMeetingRoom(meetingRoomDTO);
    }

    @UpdateMeetingRoom
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingRoomDTO updateMeetingRoom(@PathVariable Long id, @RequestBody MeetingRoomDTO meetingRoomDTO) {
        return meetingRoomRestService.saveMeetingRoomByDTO(id, meetingRoomDTO);
    }

    @UpdateMeetingRoom
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingRoomDTO patchMeetingRoom(@PathVariable Long id, @RequestBody MeetingRoomDTO meetingRoomDTO) {
        return meetingRoomRestService.patchMeetingRoom(id, meetingRoomDTO);
    }

    @DeleteMeetingRoom
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMeetingRoom(@PathVariable Long id) {
        meetingRoomRestService.deleteMeetingRoomById(id);
    }
}
