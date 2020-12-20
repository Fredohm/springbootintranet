package org.fredohm.springbootintranet.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "meeting_rooms")
public class MeetingRoom extends BaseEntity {

    @Builder
    public MeetingRoom(long id, String name, Integer capacity, String location,
                       String description, boolean available, Set<Meeting> meetings) {
        super(id);
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.description = description;
        this.available = available;
        this.meetings = meetings;
    }

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @Min(2)
    @Column(name = "capacity")
    private Integer capacity;

    @NotBlank
    @Column(name = "location")
    private String location;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "available")
    private Boolean available;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "meetingRoom", fetch = FetchType.EAGER)
    private Set<Meeting> meetings = new HashSet<>();

}
