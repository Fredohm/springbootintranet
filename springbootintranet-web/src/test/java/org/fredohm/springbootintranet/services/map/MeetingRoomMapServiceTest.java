package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRoomMapServiceTest {

    MeetingRoomMapService meetingRoomMapService;
    final Long meetingRoomId = 1L;

    @BeforeEach
    void setUp() {
        meetingRoomMapService = new MeetingRoomMapService();
        meetingRoomMapService.save(MeetingRoom.builder().id(meetingRoomId).build());

    }

    @Test
    void findAll() {
        Set<MeetingRoom> meetingRooms = meetingRoomMapService.findAll();
        assertEquals(1, meetingRooms.size());
    }

    @Test
    void findById() {
        MeetingRoom meetingRoom = meetingRoomMapService.findById(meetingRoomId);
        assertEquals(meetingRoomId, meetingRoom.getId());
    }

    @Test
    void save() {
        Long id = 2L;
        MeetingRoom meetingRoom2 =meetingRoomMapService.save(MeetingRoom.builder().id(id).build());
        MeetingRoom savedMeetingRoom = meetingRoomMapService.save(meetingRoom2);
        assertEquals(id, savedMeetingRoom.getId());
    }

    @Test
    void delete() {
        meetingRoomMapService.delete(meetingRoomMapService.findById(meetingRoomId));
        assertEquals(0, meetingRoomMapService.findAll().size());
    }

    @Test
    void deleteById() {
        meetingRoomMapService.deleteById(meetingRoomId);
        assertEquals(0, meetingRoomMapService.findAll().size());
    }
}