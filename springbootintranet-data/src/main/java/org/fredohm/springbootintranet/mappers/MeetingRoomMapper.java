package org.fredohm.springbootintranet.mappers;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateMapper.class)
public interface MeetingRoomMapper {

    MeetingRoomMapper INSTANCE = Mappers.getMapper(MeetingRoomMapper.class);

    MeetingRoomDTO meetingRoomToMeetingRoomDTO(MeetingRoom meetingRoom);

    MeetingRoom meetingRoomDtoToMeetingRoom(MeetingRoomDTO meetingRoomDTO);
}
