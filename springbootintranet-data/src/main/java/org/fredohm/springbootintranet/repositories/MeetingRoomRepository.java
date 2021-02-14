package org.fredohm.springbootintranet.repositories;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {

    List<MeetingRoom> findAllByOrderByNameAsc();

    List<MeetingRoom> findAllByAvailableIsTrueOrderByNameAsc();
}
