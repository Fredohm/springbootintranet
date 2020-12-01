package org.fredohm.springbootintranet.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"meetingRoom", "user"})
@Table(name = "meetings")
public class Meeting extends BaseEntity {

    @Builder
    public Meeting(Long id, String title, String contact, Integer membersNb, LocalDate date,
                   LocalDate start,LocalDate end, Boolean drinks, Boolean food, Boolean projection,
                   String notes, MeetingRoom meetingRoom, User user) {
        super(id);
        this.title = title;
        this.contact = contact;
        this.membersNb = membersNb;
        this.date = date;
        this.start = start;
        this.end = end;
        this.drinks = drinks;
        this.food = food;
        this.projection = projection;
        this.notes = notes;
        this.meetingRoom = meetingRoom;
        this.user = user;
    }

    @Column(name = "title")
    private String title;

    @Column(name = "contact")
    private String contact;

    @Column(name = "members_nb")
    private Integer membersNb;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Column(name = "start")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate start;

    @Column(name = "end")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate end;

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
/*
    public String toString() {
        return "Meeting(title="
                + this.getTitle()
                + ", contact=" + this.getContact()
                + ", membersNb=" + this.getMembersNb()
                + ", date=" + this.getDate()
                + ", start=" + this.getStart()
                + ", end=" + this.getEnd()
                + ", drinks=" + this.getDrinks()
                + ", food=" + this.getFood()
                + ", projection=" + this.getProjection()
                + ", notes=" + this.getNotes()
                + ", meetingRoom=" + this.getMeetingRoom().getName()
                + ", user=" + this.getUser().getUsername() + ")";
    }*/
}
