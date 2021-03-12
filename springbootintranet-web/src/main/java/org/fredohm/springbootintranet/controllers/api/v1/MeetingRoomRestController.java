package org.fredohm.springbootintranet.controllers.api.v1;


import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.model.MeetingRoomListDTO;
import org.fredohm.springbootintranet.services.api.v1.MeetingRoomRestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(MeetingRoomRestController.BASE_URL)
public class MeetingRoomRestController {

    public static final String BASE_URL = "/api/v1/meeting_rooms";
    private final MeetingRoomRestService meetingRoomRestService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MeetingRoomListDTO getListOfMeetingRooms() {
        return new MeetingRoomListDTO((meetingRoomRestService.getAllMeetingRooms()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MeetingRoomDTO getMeetingRoomById(@PathVariable Long id) {
        return meetingRoomRestService.getMeetingRoomById(id);
    }
}
