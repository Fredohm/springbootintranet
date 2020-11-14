package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class MeetingSDJpaService implements MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingSDJpaService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public Set<Meeting> findAll() {

        Set<Meeting> meetings = new HashSet<>();
        meetingRepository.findAll().forEach(meetings::add);
        return meetings;
    }

    @Override
    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElse(null);
    }

    @Override
    public Meeting save(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Override
    public void delete(Meeting meeting) {
        meetingRepository.delete(meeting);
    }

    @Override
    public void deleteById(Long id) {
        meetingRepository.deleteById(id);
    }
}
