package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MeetingMapServiceTest {

    MeetingMapService meetingMapService;
    UserMapService userMapService;
    MeetingRoomMapService meetingRoomMapService;

    final Long meetingId = 1L;
    final Long meetingRoomId = 1L;
    final Long userId = 1L;

    @BeforeEach
    void setUp() {
        meetingMapService = new MeetingMapService(meetingRoomMapService);
        meetingMapService.save(Meeting.builder().id(meetingId).build());

        meetingRoomMapService = new MeetingRoomMapService();
        userMapService = new UserMapService();
    }

    @Test
    void findAll() {
        Set<Meeting> meetings = meetingMapService.findAll();
        assertEquals(1, meetings.size());
    }

    @Test
    void findById() {
        Meeting meeting = meetingMapService.findById(meetingId);
        assertEquals(meetingId, meeting.getId());
    }

    @Test
    void save() {
        Long id = 2L;
        MeetingRoom savedMeetingRoom = meetingRoomMapService.save(MeetingRoom.builder().id(meetingRoomId).build());
        User savedUser = userMapService.save(User.builder().id(userId).build());
        Meeting savedMeeting = Meeting.builder().id(id).meetingRoom(savedMeetingRoom).user(savedUser).build();
        assertNotNull(savedMeeting.getMeetingRoom());
        assertNotNull(savedMeeting.getUser());
        assertEquals(id, savedMeeting.getId());
    }

    @Test
    void delete() {
        meetingMapService.delete(meetingMapService.findById(meetingId));
        assertEquals(0, meetingMapService.findAll().size());
    }

    @Test
    void deleteById() {
        meetingMapService.deleteById(meetingId);
        assertEquals(0, meetingMapService.findAll().size());
    }
}