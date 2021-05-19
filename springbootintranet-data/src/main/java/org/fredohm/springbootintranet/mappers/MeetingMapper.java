package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface MeetingMapper {

    MeetingMapper INSTANCE = Mappers.getMapper(MeetingMapper.class);

    MeetingDTO meetingToMeetingDTO(Meeting meeting);

    Meeting meetingDtoToMeeting(MeetingDTO meetingDTO);
}
