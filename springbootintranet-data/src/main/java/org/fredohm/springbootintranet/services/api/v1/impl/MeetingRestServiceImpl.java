package org.fredohm.springbootintranet.services.api.v1.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.exceptions.AlreadyBookedException;
import org.fredohm.springbootintranet.exceptions.ResourceNotFoundException;
import org.fredohm.springbootintranet.mappers.MeetingMapper;
import org.fredohm.springbootintranet.mappers.MeetingRoomMapper;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.api.v1.MeetingRestService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Profile({"dev", "h2"})
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

        if (isAvailable(meetingDTO)) {
            return saveAndReturnDTO(meetingMapper.meetingDtoToMeeting(meetingDTO));
        } else {
            throw new AlreadyBookedException("Une autre réunion est réservée dans la même tranche horaire!");
        }
    }

    @Transactional
    @Override
    public MeetingDTO updateMeeting(Long id, MeetingDTO meetingDTO) {
        return saveAndReturnDTO(meetingMapper.meetingDtoToMeeting(meetingDTO));
    }

    @Transactional
    @Override
    public MeetingDTO patchMeeting(Long id, MeetingDTO meetingDTO) {

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
            if (meeting.getDrinks() != null) {
                meeting.setDrinks(meetingDTO.getDrinks());
            }
            if (meeting.getFood() != null) {
                meeting.setFood(meetingDTO.getFood());
            }
            if (meeting.getProjection() != null) {
                meeting.setProjection(meetingDTO.getProjection());
            }
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

    private Boolean isAvailable(MeetingDTO meetingDTO) {
        boolean available = true;
        List<MeetingDTO> existingMeetings =  meetingRepository.findAll().stream().map(meetingMapper::meetingToMeetingDTO).collect(Collectors.toList());
        for (MeetingDTO meetingToCheck : existingMeetings) {
            if ((meetingToCheck.getMeetingRoomDTO()).equals(meetingDTO.getMeetingRoomDTO())) {
                if ((meetingToCheck.getDate()).equals(meetingDTO.getDate())) {
                    log.debug("même salle, même jour");
                    if ((meetingToCheck.getEnd().isAfter(meetingDTO.getStart()))) {
                        log.error("la réunion précédente n'est pas terminée");
                        available = false;
                    }
                }
            }
        }
        return available;
    }

}
