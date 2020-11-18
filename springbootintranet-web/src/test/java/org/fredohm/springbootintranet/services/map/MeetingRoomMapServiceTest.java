package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomMapServiceTest {

    MeetingRoomMapService meetingRoomMapService;
    final Long meetingRoomId = 1L;

    @BeforeEach
    void setUp() {
        meetingRoomMapService = new MeetingRoomMapService();
        meetingRoomMapService.save(new MeetingRoom());

    }

    @Test
    void findAll() {
        Set<MeetingRoom> meetingRooms = meetingRoomMapService.findAll();
        assertEquals(1, meetingRooms.size());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}