package org.fredohm.springbootintranet.services.sdjpa.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.exceptions.AlreadyBookedException;
import org.fredohm.springbootintranet.exceptions.NotFoundException;
import org.fredohm.springbootintranet.mappers.MeetingMapper;
import org.fredohm.springbootintranet.model.MeetingDTO;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.services.sdjpa.MeetingSDJpaService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Profile({"dev", "prod", "springdatajpa"})
public class MeetingSDJpaServiceImpl implements MeetingSDJpaService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    @Transactional
    @Override
    public List<MeetingDTO> findAll() {
        return meetingRepository.findAll().stream().map(meetingMapper::meetingToMeetingDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<MeetingDTO> findByOrderByDateAsc() {
        return meetingRepository.findByOrderByDateAsc().stream().map(meetingMapper::meetingToMeetingDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<MeetingDTO> findByDateAfterOrderByDateAsc(LocalDate date) {
        return meetingRepository.findByDateAfterOrderByDateAsc(date).stream().map(meetingMapper::meetingToMeetingDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public MeetingDTO findById(Long id) {
        Optional<Meeting> meetingToFind = meetingRepository.findById(id);

        if (meetingToFind.isEmpty()) {
            throw new NotFoundException("meeting not found for ID value " + id.toString());
        }
        return meetingMapper.meetingToMeetingDTO(meetingToFind.get());
    }

    @Transactional
    @Override
    public MeetingDTO save(MeetingDTO meetingDTO) {

        if (meetingDTO.getId() != null) {
            return  meetingMapper.meetingToMeetingDTO(meetingRepository.save(meetingMapper.meetingDtoToMeeting(meetingDTO)));
        } else {
            if (isAvailable(meetingDTO)) {
                return meetingMapper.meetingToMeetingDTO(meetingRepository.save(meetingMapper.meetingDtoToMeeting(meetingDTO)));
            } else {
                throw new AlreadyBookedException("Une autre réunion est réservée dans la même tranche horaire!");
            }
        }
    }

    private Boolean isAvailable(MeetingDTO meetingDTO) {
        boolean bool = true;
        List<MeetingDTO> existingMeetings =  meetingRepository.findAll().stream().map(meetingMapper::meetingToMeetingDTO).collect(Collectors.toList());
        for (MeetingDTO meetingToCheck : existingMeetings) {
            if ((meetingToCheck.getMeetingRoomDTO()).equals(meetingDTO.getMeetingRoomDTO())) {
                if ((meetingToCheck.getDate()).equals(meetingDTO.getDate())) {
                    log.debug("même salle, même jour");
                    if ((meetingToCheck.getEnd().isAfter(meetingDTO.getStart()))) {
                        log.error("la réunion précédente n'est pas terminée");
                        bool = false;
                    }
                }
            }
        }
        return bool;
    }

    @Transactional
    @Override
    public void delete(MeetingDTO meetingDTO) {
        meetingRepository.delete(meetingMapper.meetingDtoToMeeting(meetingDTO));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        findById(id);

        meetingRepository.deleteById(id);
    }
}
