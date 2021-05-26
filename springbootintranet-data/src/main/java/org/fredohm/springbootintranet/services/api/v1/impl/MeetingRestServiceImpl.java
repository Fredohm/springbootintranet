package org.fredohm.springbootintranet.services.api.v1.impl;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.exceptions.ResourceNotFoundException;
import org.fredohm.springbootintranet.mappers.MeetingMapper;
import org.fredohm.springbootintranet.mappers.MeetingRoomMapper;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.api.v1.MeetingRestService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Primary
@Service
public class MeetingRestServiceImpl implements MeetingRestService {

    private final MeetingMapper meetingMapper;
    private final MeetingRepository meetingRepository;

    private final MeetingRoomMapper meetingRoomMapper;
    private final MeetingRoomRepository meetingRoomRepository;

    @Transactional
    @Override
    public List<MeetingDTO> getAllMeetings() {
        return meetingRepository.findAll()
                .stream()
                .map(meetingMapper::meetingToMeetingDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<MeetingDTO> getMeetingByDateAfterOrderByDateAsc(LocalDate date) {
        return meetingRepository.findByDateAfterOrderByDateAsc(date).stream().map(meetingMapper::meetingToMeetingDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public MeetingDTO getMeetingById(Long id) {
        return meetingMapper.meetingToMeetingDTO(meetingRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @Transactional
    @Override
    public MeetingDTO createNewMeeting(MeetingDTO meetingDTO) {
        return saveAndReturnDTO(meetingMapper.meetingDtoToMeeting(meetingDTO));
    }

    @Transactional
    @Override
    public MeetingDTO saveMeetingByDTO(Long id, MeetingDTO meetingDTO) {
        Meeting meeting = meetingMapper.meetingDtoToMeeting(meetingDTO);
        meeting.setId(id);

        return saveAndReturnDTO(meeting);
    }

    @Transactional
    @Override
    public MeetingDTO patchMeeting(Long id, MeetingDTO meetingDTO) {

        System.out.println(id);
        System.out.println(meetingDTO.toString());

        return meetingRepository.findById(id).map(meeting -> {

            MeetingRoomDTO meetingRoomDTO = meetingDTO.getMeetingRoomDTO();

            if (meetingRoomDTO != null) {
                meeting.setMeetingRoom(meetingRoomMapper.meetingRoomDtoToMeetingRoom(meetingRoomDTO));
            }

            if (meeting.getTitle() != null) {
                meeting.setTitle(meetingDTO.getTitle());
            }
            if (meeting.getDate() != null) {
                meeting.setDate(meetingDTO.getDate());
            }
            if (meeting.getStart() != null) {
                meeting.setStart(meetingDTO.getStart());
            }
            if (meeting.getEnd() != null) {
                meeting.setEnd(meetingDTO.getEnd());
            }
            meeting.setDrinks(meetingDTO.getDrinks());
            meeting.setFood(meetingDTO.getFood());
            meeting.setProjection(meetingDTO.getProjection());


            return meetingMapper.meetingToMeetingDTO(meetingRepository.save(meeting));
        }).orElseThrow(ResourceNotFoundException::new);

    }

    @Transactional
    @Override
    public void deleteMeetingById(Long id) {
        meetingRepository.deleteById(id);
    }

    private MeetingDTO saveAndReturnDTO(Meeting meeting) {
        Meeting savedMeeting = meetingRepository.save(meeting);

        return meetingMapper.meetingToMeetingDTO(savedMeeting);
    }
}
