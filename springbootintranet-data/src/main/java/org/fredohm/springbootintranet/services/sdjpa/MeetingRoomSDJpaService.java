package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class MeetingRoomSDJpaService implements MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRepository meetingRepository;

    public MeetingRoomSDJpaService(MeetingRoomRepository meetingRoomRepository, MeetingRepository meetingRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingRepository = meetingRepository;
    }

    @Transactional
    @Override
    public Set<MeetingRoom> findAll() {

        Set<MeetingRoom> meetingRooms = new HashSet<>();
        meetingRoomRepository.findAll().forEach(meetingRooms::add);
        return meetingRooms;
    }

    @Transactional
    @Override
    public MeetingRoom findById(Long id) {
        return meetingRoomRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public MeetingRoom save(MeetingRoom meetingRoom) {
        return meetingRoomRepository.save(meetingRoom);
    }

    @Transactional
    @Override
    public void delete(MeetingRoom meetingRoom) {
        meetingRoomRepository.delete(meetingRoom);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        meetingRoomRepository.deleteById(id);
    }
}
