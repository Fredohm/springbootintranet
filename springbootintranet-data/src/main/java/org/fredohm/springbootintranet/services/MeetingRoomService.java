package org.fredohm.springbootintranet.services;

import org.fredohm.springbootintranet.domain.MeetingRoom;

import java.util.List;

public interface MeetingRoomService extends CrudService<MeetingRoom, Long> {

    List<MeetingRoom> findAllByOrderByNameAsc();

    List<MeetingRoom> findAllByAvailableIsTrueOrderByNameAsc();
}
