package org.fredohm.springbootintranet.services.sdjpa.impl;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.exceptions.ExistingMeetingsException;
import org.fredohm.springbootintranet.exceptions.NotFoundException;
import org.fredohm.springbootintranet.mappers.MeetingRoomMapper;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.sdjpa.MeetingRoomSDJpaService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
@Profile({"dev", "prod", "springdatajpa"})
public class MeetingRoomSDJpaServiceImpl implements MeetingRoomSDJpaService {

    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRoomMapper meetingRoomMapper;


    @Transactional
    @Override
    public Set<MeetingRoomDTO> findAll() {

        Set<MeetingRoomDTO> meetingRoomsDTO = new HashSet<>();
        meetingRoomRepository.findAll().forEach(meetingRoomMapper::meetingRoomToMeetingRoomDTO);

        return meetingRoomsDTO;
    }

    @Transactional
    @Override
    public List<MeetingRoomDTO> findAllByOrderByNameAsc() {
        List<MeetingRoomDTO> meetingRooms = new ArrayList<>();

        meetingRoomRepository.findAllByOrderByNameAsc().forEach(meetingRoomMapper::meetingRoomToMeetingRoomDTO);

        return meetingRooms;
    }

    @Transactional
    @Override
    public List<MeetingRoomDTO> findAllByAvailableIsTrueOrderByNameAsc() {
        List<MeetingRoomDTO> meetingRooms = new ArrayList<>();
        meetingRoomRepository.findAllByAvailableIsTrueOrderByNameAsc().forEach(meetingRoomMapper::meetingRoomToMeetingRoomDTO);
        return meetingRooms;
    }

    @Transactional
    @Override
    public MeetingRoomDTO findById(Long id) {

        Optional<MeetingRoom> meetingRoomToFind = meetingRoomRepository.findById(id);

        if (meetingRoomToFind.isEmpty()) {
            throw new NotFoundException("meeting-room not found for ID value " + id.toString());
        }

        return meetingRoomMapper.meetingRoomToMeetingRoomDTO(meetingRoomToFind.get());
    }

    @Transactional
    @Override
    public MeetingRoomDTO save(MeetingRoomDTO meetingRoomDTO) {
         return meetingRoomMapper.meetingRoomToMeetingRoomDTO(meetingRoomRepository.save(meetingRoomMapper.meetingRoomDtoToMeetingRoom(meetingRoomDTO)));
    }

    @Transactional
    @Override
    public void delete(MeetingRoomDTO meetingRoomDTO) {
        meetingRoomRepository.delete(meetingRoomMapper.meetingRoomDtoToMeetingRoom(meetingRoomDTO));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        findById(id);

        if (meetingRoomRepository.findById(id).get().getMeetings() != null) {
            throw new ExistingMeetingsException("There is reservations schedulded in this meeting-room!");
        }

        meetingRoomRepository.deleteById(id);
    }
}
