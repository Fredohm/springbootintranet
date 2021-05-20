package org.fredohm.springbootintranet.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "meetings")
@Entity
@Table(name = "meeting_rooms")
public class MeetingRoom extends BaseEntity {

    @Builder
    public MeetingRoom(Long id, Integer version, Timestamp createdDate, Timestamp lastModifiedDate, String name, Integer capacity, String location,
                       String description, boolean available, List<Meeting> meetings) {
        super(id, version, createdDate, lastModifiedDate);
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.description = description;
        this.available = available;
        this.meetings = meetings;
    }


    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "available")
    private Boolean available;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "meetingRoom", fetch = FetchType.EAGER)
    private List<Meeting> meetings = new ArrayList<>();

}
