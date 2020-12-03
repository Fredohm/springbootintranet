package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class MeetingMapService extends AbstractMapService<Meeting, Long> implements MeetingService {

    private final MeetingRoomService meetingRoomService;

    public MeetingMapService(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }

    @Override
    public Set<Meeting> findAll() {

        return super.findAll();
    }

    @Override
    public Meeting findById(Long id) {

        return super.findById(id);
    }

    @Transactional
    @Override
    public Meeting save(Meeting meeting) {

        return super.save(meeting);
    }

    @Override
    public void delete(Meeting meeting) {

        super.delete(meeting);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }
}
