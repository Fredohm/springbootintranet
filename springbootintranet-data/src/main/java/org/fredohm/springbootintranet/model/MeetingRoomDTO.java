package org.fredohm.springbootintranet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoomDTO {

    private Long id;
    private String name;
    private Integer capacity;
    private String location;
    private String description;
    private boolean available;
    //private List<Meeting> meetings;
}
