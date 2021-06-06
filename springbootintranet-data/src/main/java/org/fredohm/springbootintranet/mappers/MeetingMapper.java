package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface MeetingMapper {

    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    @Mapping(target = "meetingRoomDTO", source = "meetingRoom")
    @Mapping(target = "meetingRoomDTO.meetings", ignore = true)
    MeetingDTO meetingToMeetingDTO(Meeting meeting);

    @Mapping(target = "meetingRoom", source = "meetingRoomDTO")
    @Mapping(target = "meetingRoomDTO.meetings", ignore = true)
    Meeting meetingDtoToMeeting(MeetingDTO meetingDTO);
}
