package org.fredohm.springbootintranet.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class MeetingDTO extends BaseDTO {

    static final long serialVersionUID = -54254261107304646L;

    @Builder
    public MeetingDTO(Long id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String title, String contact, Integer membersNb, LocalDate date,
                      LocalTime start, LocalTime end, Boolean drinks, Boolean food, Boolean projection,
                      String notes, MeetingRoomDTO meetingRoomDTO) {
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
        this.meetingRoomDTO = meetingRoomDTO;
    }

    @NotBlank
    private String title;

    private String contact;

    @NotNull
    private Integer membersNb;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime start;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime end;

    private Boolean drinks;
    private Boolean food;
    private Boolean projection;
    private String notes;

    private MeetingRoomDTO meetingRoomDTO;
}
