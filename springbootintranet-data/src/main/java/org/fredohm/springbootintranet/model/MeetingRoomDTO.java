package org.fredohm.springbootintranet.model;

import lombok.*;

import javax.persistence.OrderBy;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
public class MeetingRoomDTO extends BaseDTO {

    static final long serialVersionUID = -3950371084128873179L;

    @Builder
    public MeetingRoomDTO(Long id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String name, Integer capacity, String location,
                          String description, boolean available, List<MeetingDTO> meetings) {
        super(id, version, createdDate, lastModifiedDate);
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.description = description;
        this.available = available;
        this.meetings = meetings;
    }

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Min(2)
    private Integer capacity;

    @NotBlank
    private String location;

    @Size(max = 255)
    private String description;

    private boolean available;

    @OrderBy("date asc")
    private List<MeetingDTO> meetings;
}
