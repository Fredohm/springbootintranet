package org.fredohm.springbootintranet.repositories;

import org.fredohm.springbootintranet.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findByOrderByDateAsc();

    List<Meeting> findByDateAfterOrderByDateAsc(LocalDate date);
}
