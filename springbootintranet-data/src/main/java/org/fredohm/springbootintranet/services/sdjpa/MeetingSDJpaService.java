package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.model.MeetingDTO;

import java.time.LocalDate;
import java.util.List;

public interface MeetingSDJpaService extends CrudService<MeetingDTO, Long> {

    List<MeetingDTO> findByOrderByDateAsc();

    List<MeetingDTO> findByDateAfterOrderByDateAsc(LocalDate date);
}
