package org.fredohm.springbootintranet.repositories;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {

}
