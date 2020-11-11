package org.fredohm.springbootintranet.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "meetings")
public class Meeting extends BaseEntity {

    @Column(name = "title")
    private String meetingTitle;

    @Column(name = "leader")
    private String meetingLeader;

    @Column(name = "members_nb")
    private Integer membersNb;

    @Column(name = "meeting_date")
    private LocalDate meetingDate;

    @Column(name = "start_time")
    private LocalDate startTime;

    @Column(name = "end_time")
    private LocalDate endTime;

    @Column(name = "drinks")
    private Boolean drinks;

    @Column(name = "food")
    private Boolean food;

    @Column(name = "projection")
    private Boolean projection;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "meeting_room_id")
    private MeetingRoom meetingRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingLeader() {
        return meetingLeader;
    }

    public void setMeetingLeader(String meetingLeader) {
        this.meetingLeader = meetingLeader;
    }

    public Integer getMembersNb() {
        return membersNb;
    }

    public void setMembersNb(Integer membersNb) {
        this.membersNb = membersNb;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Boolean getDrinks() {
        return drinks;
    }

    public void setDrinks(Boolean drinks) {
        this.drinks = drinks;
    }

    public Boolean getFood() {
        return food;
    }

    public void setFood(Boolean food) {
        this.food = food;
    }

    public Boolean getProjection() {
        return projection;
    }

    public void setProjection(Boolean projection) {
        this.projection = projection;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingTitle='" + meetingTitle + '\'' +
                ", meetingLeader='" + meetingLeader + '\'' +
                ", membersNb=" + membersNb +
                ", meetingDate=" + meetingDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", drinks=" + drinks +
                ", food=" + food +
                ", projection=" + projection +
                ", notes='" + notes + '\'' +
                ", meetingRoom=" + meetingRoom.getName() +
                ", user=" + user.getUsername() +
                '}';
    }
}
