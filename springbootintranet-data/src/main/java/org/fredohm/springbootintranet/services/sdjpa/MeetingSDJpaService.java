package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.Meeting;

import java.time.LocalDate;
import java.util.List;

public interface MeetingSDJpaService extends CrudService<Meeting, Long> {

    List<Meeting> findByOrderByDateAsc();

    List<Meeting> findByDateAfterOrderByDateAsc(LocalDate date);
}
