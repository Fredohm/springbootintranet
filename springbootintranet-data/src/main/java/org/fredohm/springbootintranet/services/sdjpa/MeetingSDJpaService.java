package org.fredohm.springbootintranet.services.sdjpa;

import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.exceptions.AlreadyBookedException;
import org.fredohm.springbootintranet.exceptions.NotFoundException;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@Profile({"dev", "prod", "springdatajpa"})
public class MeetingSDJpaService implements MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingSDJpaService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Transactional
    @Override
    public Set<Meeting> findAll() {

        Set<Meeting> meetings = new HashSet<>();
        meetingRepository.findAll().forEach(meetings::add);
        return meetings;
    }

    @Override
    public List<Meeting> findByOrderByDateAsc() {
        List<Meeting> meetings = new ArrayList<>();
        meetingRepository.findByOrderByDateAsc().forEach(meetings::add);
        return meetings;
    }

    @Override
    public List<Meeting> findByDateAfterOrderByDateAsc(LocalDate date) {
        List<Meeting> meetings = new ArrayList<>();
        meetingRepository.findByDateAfterOrderByDateAsc(date).forEach(meetings::add);
        return meetings;
    }

    @Transactional
    @Override
    public Meeting findById(Long id) {
        Optional<Meeting> meetingToFind = meetingRepository.findById(id);

        if (meetingToFind.isEmpty()) {
            throw new NotFoundException("meeting not found for ID value " + id.toString());
        }
        return meetingToFind.get();
    }

    @Transactional
    @Override
    public Meeting save(Meeting meeting) {

        if (meeting.getId() != null) {
            return meetingRepository.save(meeting);
        } else {
            if (isAvailable(meeting)) {
                return meetingRepository.save(meeting);
            } else {
                throw new AlreadyBookedException("Une autre réunion est réservée dans la même tranche horaire!");
            }
        }
    }

    private Boolean isAvailable(Meeting meeting) {
        boolean bool = true;
        List<Meeting> existingMeetings = meetingRepository.findAll();
        for (Meeting meetingToCheck : existingMeetings) {
            if ((meetingToCheck.getMeetingRoom()).equals(meeting.getMeetingRoom())) {
                if ((meetingToCheck.getDate()).equals(meeting.getDate())) {
                    log.debug("même salle, même jour");
                    if ((meetingToCheck.getEnd().isAfter(meeting.getStart()))) {
                        log.error("la réunion précédente n'est pas terminée");
                        bool = false;
                    }
                }
            }
        }
        return bool;
    }

    @Transactional
    @Override
    public void delete(Meeting meeting) {
        meetingRepository.delete(meeting);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        findById(id);

        meetingRepository.deleteById(id);
    }
}
