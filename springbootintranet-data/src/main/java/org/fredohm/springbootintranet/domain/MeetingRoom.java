package org.fredohm.springbootintranet.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meeting_rooms")
public class MeetingRoom extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private String capacity;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "available")
    private Boolean available;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meetingRoom")
    private Set<Meeting> meetings = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }
}
