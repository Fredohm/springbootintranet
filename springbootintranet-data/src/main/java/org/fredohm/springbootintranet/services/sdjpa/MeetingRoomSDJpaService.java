package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.model.MeetingRoomDTO;

import java.util.List;

public interface MeetingRoomSDJpaService extends CrudService<MeetingRoomDTO, Long> {

    List<MeetingRoomDTO> findAllByAvailableIsTrueOrderByNameAsc();
}
