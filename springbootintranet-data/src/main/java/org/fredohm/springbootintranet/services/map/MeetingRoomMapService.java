package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MeetingRoomMapService extends AbstractMapService<MeetingRoom, Long> implements MeetingRoomService {

    @Override
    public Set<MeetingRoom> findAll() {

        return super.findAll();
    }

    @Override
    public MeetingRoom findById(Long id) {

        return super.findById(id);
    }

    @Override
    public MeetingRoom save(MeetingRoom meetingRoom) {

        return super.save(meetingRoom);
    }

    @Override
    public void delete(MeetingRoom meetingRoom) {

        super.delete(meetingRoom);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }
}
