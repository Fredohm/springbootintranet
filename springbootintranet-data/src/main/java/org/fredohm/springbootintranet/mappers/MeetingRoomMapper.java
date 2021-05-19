package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface MeetingRoomMapper {

    MeetingRoomDTO meetingRoomToMeetingRoomDTO(MeetingRoom meetingRoom);

    MeetingRoom meetingRoomDtoToMeetingRoom(MeetingRoomDTO meetingRoomDTO);
}
