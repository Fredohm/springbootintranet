package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.exceptions.NotFoundException;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile({"dev", "prod", "springdatajpa"})
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

        Optional<MeetingRoom> meetingRoomToFind = meetingRoomRepository.findById(id);

        if (meetingRoomToFind.isEmpty()) {
            throw new NotFoundException("meeting-room not found for ID value " + id.toString());
        }

        return meetingRoomToFind.get();
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
