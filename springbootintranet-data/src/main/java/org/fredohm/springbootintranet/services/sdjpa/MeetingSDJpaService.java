package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

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

    @Transactional
    @Override
    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Meeting save(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Transactional
    @Override
    public void delete(Meeting meeting) {
        meetingRepository.delete(meeting);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        meetingRepository.deleteById(id);
    }
}
