package org.fredohm.springbootintranet.services.sdjpa;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.repositories.MeetingRepository;
import org.fredohm.springbootintranet.repositories.MeetingRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeetingRoomSDJpaServiceTest {

    @Mock
    MeetingRoomRepository meetingRoomRepository;

    @Mock
    MeetingRepository meetingRepository;

    @InjectMocks
    MeetingRoomSDJpaService service;

    MeetingRoom returnMeetingRoom;

    @BeforeEach
    void setUp() {
        returnMeetingRoom = MeetingRoom.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<MeetingRoom> returnMeetingRooms = new HashSet<>();
        returnMeetingRooms.add(MeetingRoom.builder().id(1L).build());
        returnMeetingRooms.add(MeetingRoom.builder().id(2L).build());
        when(meetingRoomRepository.findAll()).thenReturn(returnMeetingRooms);

        Set<MeetingRoom> meetingRooms = service.findAll();
        assertNotNull(meetingRooms);
        assertEquals(2, meetingRooms.size());
    }

    @Test
    void findById() {
        when(meetingRoomRepository.findById(anyLong())).thenReturn(Optional.of(returnMeetingRoom));

        MeetingRoom meetingRoom = service.findById(1L);
        assertNotNull(meetingRoom);
    }

    @Test
    void findByIdNotFound() {
        when(meetingRoomRepository.findById(anyLong())).thenReturn(Optional.empty());

        MeetingRoom meetingRoom = service.findById(1L);
        assertNull(meetingRoom);
    }

    @Test
    void save() {
        MeetingRoom meetingRoomToSave = MeetingRoom.builder().id(1L).build();
        when(meetingRoomRepository.save(any())).thenReturn(returnMeetingRoom);

        MeetingRoom savedMeetingRoom = service.save(meetingRoomToSave);
        assertNotNull(savedMeetingRoom);
        verify(meetingRoomRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnMeetingRoom);
        verify(meetingRoomRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(meetingRoomRepository).deleteById(anyLong());
    }
}