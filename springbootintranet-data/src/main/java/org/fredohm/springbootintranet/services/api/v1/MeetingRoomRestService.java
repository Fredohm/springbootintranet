package org.fredohm.springbootintranet.services.api.v1;

import org.fredohm.springbootintranet.model.MeetingRoomDTO;

import java.util.List;

public interface MeetingRoomRestService {

    List<MeetingRoomDTO> getAllMeetingRooms();

    List<MeetingRoomDTO> getAllMeetingRoomsByAvailableIsTrueOrderByNameAsc();

    MeetingRoomDTO getMeetingRoomById(Long id);

    MeetingRoomDTO createNewMeetingRoom(MeetingRoomDTO meetingRoomDTO);

    MeetingRoomDTO saveMeetingRoomByDTO(Long id, MeetingRoomDTO meetingRoomDTO);

    MeetingRoomDTO patchMeetingRoom(Long id, MeetingRoomDTO meetingRoomDTO);

    void deleteMeetingRoomById(Long id);
}
