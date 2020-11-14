package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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

    @Override
    public Set<MeetingRoom> findAll() {

        Set<MeetingRoom> meetingRooms = new HashSet<>();
        meetingRoomRepository.findAll().forEach(meetingRooms::add);
        return meetingRooms;
    }

    @Override
    public MeetingRoom findById(Long id) {
        return meetingRoomRepository.findById(id).orElse(null);
    }

    @Override
    public MeetingRoom save(MeetingRoom meetingRoom) {
        return meetingRoomRepository.save(meetingRoom);
    }

    @Override
    public void delete(MeetingRoom meetingRoom) {
        meetingRoomRepository.delete(meetingRoom);
    }

    @Override
    public void deleteById(Long id) {
        meetingRoomRepository.deleteById(id);
    }
}
