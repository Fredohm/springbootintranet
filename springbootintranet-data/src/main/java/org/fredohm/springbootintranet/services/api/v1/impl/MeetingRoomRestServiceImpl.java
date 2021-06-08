package org.fredohm.springbootintranet.services.api.v1.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.exceptions.ExistingMeetingsException;
import org.fredohm.springbootintranet.exceptions.NotFoundException;
import org.fredohm.springbootintranet.exceptions.ResourceNotFoundException;
import org.fredohm.springbootintranet.mappers.MeetingRoomMapper;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.api.v1.MeetingRoomRestService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Profile({"dev", "h2"})
public class  MeetingRoomRestServiceImpl implements MeetingRoomRestService {

    private final MeetingRoomMapper meetingRoomMapper;
    private final MeetingRoomRepository meetingRoomRepository;

    @Transactional
    @Override
    public List<MeetingRoomDTO> getAllMeetingRooms() {
        return meetingRoomRepository.findAll()
                .stream()
                .map(meetingRoomMapper::meetingRoomToMeetingRoomDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<MeetingRoomDTO> getAllMeetingRoomsByAvailableIsTrueOrderByNameAsc() {
        return meetingRoomRepository.findAllByAvailableIsTrueOrderByNameAsc().stream().map(meetingRoomMapper::meetingRoomToMeetingRoomDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public MeetingRoomDTO getMeetingRoomById(Long id) {

        Optional<MeetingRoom> meetingRoomToFind = meetingRoomRepository.findById(id);

        if (meetingRoomToFind.isEmpty()) {
            throw new NotFoundException("meeting-room not found for ID value " + id);
        }

        return meetingRoomMapper.meetingRoomToMeetingRoomDTO(meetingRoomRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @Transactional
    @Override
    public MeetingRoomDTO createNewMeetingRoom(MeetingRoomDTO meetingRoomDTO) {
        meetingRoomDTO.setMeetings(new ArrayList<>());
        return saveAndReturnDTO(meetingRoomMapper.meetingRoomDtoToMeetingRoom(meetingRoomDTO));
    }

    @Transactional
    @Override
    public MeetingRoomDTO updateMeetingRoom(Long id, MeetingRoomDTO meetingRoomDTO) {

        return saveAndReturnDTO(meetingRoomMapper.meetingRoomDtoToMeetingRoom(meetingRoomDTO));
    }

    @Transactional
    @Override
    public MeetingRoomDTO patchMeetingRoom(Long id, MeetingRoomDTO meetingRoomDTO) {

        return meetingRoomRepository.findById(id).map(meetingRoom -> {
            if (meetingRoomDTO.getName() != null) {
                meetingRoom.setName(meetingRoomDTO.getName());
            }
            if (meetingRoomDTO.getCapacity() != null) {
                meetingRoom.setCapacity(meetingRoomDTO.getCapacity());
            }
            if (meetingRoomDTO.getDescription() != null) {
                meetingRoom.setDescription(meetingRoomDTO.getDescription());
            }
            if (meetingRoomDTO.getLocation() != null) {
                meetingRoom.setLocation(meetingRoomDTO.getLocation());
            }
            // TODO Add logic
            meetingRoom.setAvailable(meetingRoomDTO.isAvailable());

            return meetingRoomMapper.meetingRoomToMeetingRoomDTO(meetingRoomRepository.save(meetingRoom));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    @Override
    public void deleteMeetingRoomById(Long id) {
        if (meetingRoomRepository.findById(id).get().getMeetings() != null && meetingRoomRepository.findById(id).get().getMeetings().size() > 0) {
            throw new ExistingMeetingsException("There is reservations schedulded in this meeting-room!");
        }

        meetingRoomRepository.deleteById(id);
    }

    private MeetingRoomDTO saveAndReturnDTO(MeetingRoom meetingRoom) {
        MeetingRoom savedMeetingRoom = meetingRoomRepository.save(meetingRoom);

        return meetingRoomMapper.meetingRoomToMeetingRoomDTO(savedMeetingRoom);
    }
}
