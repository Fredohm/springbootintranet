package org.fredohm.springbootintranet.services;

import org.fredohm.springbootintranet.domain.Meeting;

import java.util.List;

public interface MeetingService extends CrudService<Meeting, Long> {

    List<Meeting> findByOrderByDateAsc();
}
