package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface MeetingMapper {

    MeetingDTO meetingToMeetingDTO(Meeting meeting);

    Meeting meetingDtoToMeeting(MeetingDTO meetingDTO);
}
