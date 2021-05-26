package org.fredohm.springbootintranet.services.api.v1;

import org.fredohm.springbootintranet.model.MeetingDTO;

import java.time.LocalDate;
import java.util.List;

public interface MeetingRestService {

    List<MeetingDTO> getAllMeetings();

    List<MeetingDTO> getMeetingByDateAfterOrderByDateAsc(LocalDate date);

    MeetingDTO getMeetingById(Long id);

    MeetingDTO createNewMeeting(MeetingDTO meetingDTO);

    MeetingDTO saveMeetingByDTO(Long id, MeetingDTO meetingRoomDTO);

    MeetingDTO patchMeeting(Long id, MeetingDTO meetingDTO);

    void deleteMeetingById(Long id);
}
