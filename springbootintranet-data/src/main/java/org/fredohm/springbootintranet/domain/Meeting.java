package org.fredohm.springbootintranet.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = "meetingRoom")
@Table(name = "meetings")
public class Meeting extends BaseEntity {

    @Builder
    public Meeting(Long id, Integer version, Timestamp createdDate, Timestamp lastModifiedDate, String title, String contact, Integer membersNb, LocalDate date,
                   LocalTime start, LocalTime end, boolean drinks, boolean food, boolean projection,
                   String notes, MeetingRoom meetingRoom) {
        super(id, version, createdDate, lastModifiedDate);
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
    }


    @Column(name = "title")
    private String title;

    @Column(name = "contact")
    private String contact;


    @Column(name = "members_nb")
    private Integer membersNb;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "start")
    private LocalTime start;


    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "end")
    private LocalTime end;

    @Column(name = "drinks")
    private boolean drinks;

    @Column(name = "food")
    private boolean food;

    @Column(name = "projection")
    private boolean projection;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "meeting_room_id")
    private MeetingRoom meetingRoom;
}
