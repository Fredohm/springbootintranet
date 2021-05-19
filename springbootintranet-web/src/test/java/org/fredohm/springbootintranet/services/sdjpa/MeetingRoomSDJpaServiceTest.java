package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.model.MeetingRoomDTO;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.fredohm.springbootintranet.services.sdjpa.impl.MeetingRoomSDJpaServiceImpl;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(MockitoExtension.class)
class MeetingRoomSDJpaServiceTest {

    final Long meetingRoomId = 1L;

    @Mock
    MeetingRoomRepository meetingRoomRepository;

    @Mock
    MeetingRepository meetingRepository;

    @InjectMocks
    MeetingRoomSDJpaServiceImpl service;

    MeetingRoom returnMeetingRoom;

    @BeforeEach
    void setUp() {
        returnMeetingRoom = MeetingRoom.builder().id(meetingRoomId).build();
    }

    @Test
    void findAll() {
        List<MeetingRoom> returnMeetingRooms = new ArrayList<>();
        returnMeetingRooms.add(MeetingRoom.builder().id(1L).build());
        returnMeetingRooms.add(MeetingRoom.builder().id(2L).build());
        when(meetingRoomRepository.findAll()).thenReturn(returnMeetingRooms);

        Set<MeetingRoomDTO> meetingRooms = service.findAll();
        assertNotNull(meetingRooms);
        assertEquals(2, meetingRooms.size());
    }

    @Test
    void findById() {
        when(meetingRoomRepository.findById(anyLong())).thenReturn(Optional.of(returnMeetingRoom));

        //MeetingRoom meetingRoom = service.findById(meetingRoomId);
        //assertNotNull(meetingRoom);
    }

    @Test
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void findByIdNotFound() {
        when(meetingRoomRepository.findById(anyLong())).thenReturn(Optional.of(returnMeetingRoom));

        //MeetingRoom meetingRoom = service.findById(meetingRoomId);

        //assertNotNull(meetingRoom);
    }

    @Disabled
    @Test
    void save() {
        MeetingRoom meetingRoomToSave = MeetingRoom.builder().id(meetingRoomId).build();
        when(meetingRoomRepository.save(any())).thenReturn(returnMeetingRoom);

        //MeetingRoom savedMeetingRoom = service.save(meetingRoomToSave);
        //assertNotNull(savedMeetingRoom);
        verify(meetingRoomRepository).save(any());
    }

    @Disabled
    @Test
    void delete() {
        //service.delete(returnMeetingRoom);
        verify(meetingRoomRepository).delete(any());
    }

    @Disabled
    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(meetingRoomRepository).deleteById(anyLong());
    }
}