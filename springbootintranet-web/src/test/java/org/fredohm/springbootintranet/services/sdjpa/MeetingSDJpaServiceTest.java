package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(MockitoExtension.class)
class MeetingSDJpaServiceTest {

    final Long meetingId = 1L;

    @Mock
    MeetingRepository meetingRepository;

    @Mock
    MeetingRoomRepository meetingRoomRepository;


    @InjectMocks
    //MeetingSDJpaServiceImpl service;

    Meeting returnMeeting;

    @BeforeEach
    void setUp() {
        returnMeeting = Meeting.builder().id(meetingId).build();
    }

    @Disabled
    @Test
    void findAll() {
        List<Meeting> returnMeetings = new ArrayList<>();
        returnMeetings.add(Meeting.builder().id(1L).build());
        returnMeetings.add(Meeting.builder().id(2L).build());
        when(meetingRepository.findAll()).thenReturn(returnMeetings);

//        Set<Meeting> meetings = service.findAll();
//        assertNotNull(meetings);
//        assertEquals(2, meetings.size());
    }

    @Disabled
    @Test
    void findById() {
        when(meetingRepository.findById(anyLong())).thenReturn(Optional.of(returnMeeting));

//        Meeting meeting = service.findById(meetingId);
//        assertNotNull(meeting);
    }

    @Disabled
    @Test
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void findByIdNotFound() {
        when(meetingRepository.findById(anyLong())).thenReturn(Optional.of(returnMeeting));

//        Meeting meeting = service.findById(meetingId);

//        assertNotNull(meeting);
    }

    @Disabled
    @Test
    void save() {
        Meeting meetingToSave = Meeting.builder().id(meetingId).build();
        when(meetingRepository.save(any())).thenReturn(returnMeeting);

//        Meeting savedMeeting = service.save(meetingToSave);
//        assertNotNull(savedMeeting);
        verify(meetingRepository).save(any());
    }

    @Test
    void delete() {
        //service.delete(returnMeeting);
        verify(meetingRepository).delete(any());
    }

    @Disabled
    @Test
    void deleteById() {
        //service.deleteById(meetingId);
        verify(meetingRepository).deleteById(anyLong() );
    }
}