package org.fredohm.springbootintranet.repositories;

import org.fredohm.springbootintranet.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
