package org.fredohm.springbootintranet.services.map;

import org.fredohm.springbootintranet.domain.Meeting;
import org.fredohm.springbootintranet.domain.MeetingRoom;
import org.fredohm.springbootintranet.services.MeetingRoomService;
import org.fredohm.springbootintranet.services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class MeetingRoomMapService extends AbstractMapService<MeetingRoom, Long> implements MeetingRoomService {

    private MeetingService meetingService;

    @Autowired
    public void setMeetingService(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Transactional
    @Override
    public Set<MeetingRoom> findAll() {

        return super.findAll();
    }

    @Transactional
    @Override
    public MeetingRoom findById(Long id) {

        return super.findById(id);
    }

    @Transactional
    @Override
    public MeetingRoom save(MeetingRoom meetingRoom) {

        return super.save(meetingRoom);
    }

    @Transactional
    @Override
    public void delete(MeetingRoom meetingRoom) {

        super.delete(meetingRoom);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        MeetingRoom meetingRoomToDelete = this.findById(id);
        Set<Meeting> meetingsToDelete = meetingRoomToDelete.getMeetings();

        if (meetingsToDelete != null && meetingsToDelete.size() >0) {
            for (Meeting meetingToDelete : meetingsToDelete) {
                meetingService.deleteById(meetingToDelete.getId());
            }
        }



        super.deleteById(id);
    }
}
