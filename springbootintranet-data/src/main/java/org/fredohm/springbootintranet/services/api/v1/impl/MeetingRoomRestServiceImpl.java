package org.fredohm.springbootintranet.services.api.v1.impl;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.exceptions.ResourceNotFoundException;
import org.fredohm.springbootintranet.mappers.MeetingRoomMapper;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.api.v1.MeetingRoomRestService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Primary
@Service
public class MeetingRoomRestServiceImpl implements MeetingRoomRestService {

    private final MeetingRoomMapper meetingRoomMapper;
    private final MeetingRoomRepository meetingRoomRepository;

    @Override
    public List<MeetingRoomDTO> getAllMeetingRooms() {
        return meetingRoomRepository.findAll()
                .stream()
                .map(meetingRoom -> {
                    MeetingRoomDTO meetingRoomDTO = meetingRoomMapper.meetingRoomToMeetingRoomDTO(meetingRoom);
                    return meetingRoomDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public MeetingRoomDTO getMeetingRoomById(Long id) {
        return meetingRoomMapper.meetingRoomToMeetingRoomDTO(meetingRoomRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));

    }

    @Override
    public MeetingRoomDTO createNewMeetingRoom(MeetingRoomDTO meetingRoomDTO) {
        return null;
    }

    @Override
    public MeetingRoomDTO saveMeetingRoomByDTO(Long id, MeetingRoomDTO meetingRoomDTO) {
        return null;
    }

    @Override
    public MeetingRoomDTO patchMeetingRoom(Long id, MeetingRoomDTO meetingRoomDTO) {
        return null;
    }

    @Override
    public void deleteMeetingRoomById(Long id) {

    }
}
