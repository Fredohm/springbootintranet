package org.fredohm.springbootintranet.services.api.v1.impl;

import lombok.RequiredArgsConstructor;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.exceptions.ResourceNotFoundException;
import org.fredohm.springbootintranet.mappers.MeetingMapper;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.services.api.v1.MeetingRestService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Primary
@Service
public class MeetingRestServiceImpl implements MeetingRestService {

    private final MeetingMapper meetingMapper;
    private final MeetingRepository meetingRepository;
    private MeetingRestServiceImpl meetingRoomRepository;

    @Override
    public List<MeetingDTO> getAllMeetings() {
        return meetingRepository.findAll()
                .stream()
                .map(meetingMapper::meetingToMeetingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MeetingDTO getMeetingById(Long id) {
        return meetingMapper.meetingToMeetingDTO(meetingRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public MeetingDTO createNewMeeting(MeetingDTO meetingDTO) {
        return saveAndReturnDTO(meetingMapper.meetingDtoToMeeting(meetingDTO));
    }

    @Override
    public MeetingDTO saveMeetingByDTO(Long id, MeetingDTO meetingDTO) {
        Meeting meeting = meetingMapper.meetingDtoToMeeting(meetingDTO);
        meeting.setId(id);

        return saveAndReturnDTO(meeting);
    }

    @Override
    public MeetingDTO patchMeeting(Long id, MeetingDTO meetingDTO) {
        return meetingRepository.findById(id).map(meeting -> {
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

    @Override
    public void deleteMeetingById(Long id) {

    }

    private MeetingDTO saveAndReturnDTO(Meeting meeting) {
        Meeting savedMeeting = meetingRepository.save(meeting);

        return meetingMapper.meetingToMeetingDTO(savedMeeting);
    }
}
