package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
public interface MeetingMapper {

    @Mapping(target = "meetingRoomDTO", source = "meetingRoom")
    @Mapping(target = "meetingRoomDTO.meetings", ignore = true)
    MeetingDTO meetingToMeetingDTO(Meeting meeting);

    @Mapping(target = "meetingRoom", source = "meetingRoomDTO")
    @Mapping(target = "meetingRoomDTO.meetings", ignore = true)
    Meeting meetingDtoToMeeting(MeetingDTO meetingDTO);
}
