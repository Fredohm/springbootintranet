package org.fredohm.springbootintranet.services;

import org.fredohm.springbootintranet.domain.Meeting;

import java.time.LocalDate;
import java.util.List;

public interface MeetingService extends CrudService<Meeting, Long> {

    List<Meeting> findByOrderByDateAsc();

    List<Meeting> findByDateAfterOrderByDateAsc(LocalDate date);
}
