package org.fredohm.springbootintranet.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingDTO extends BaseDTO {

    static final long serialVersionUID = -54254261107304646L;

    @NotBlank
    String title;

    String contact;

    @NotNull
    Integer membersNb;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime start;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime end;

    boolean drinks;
    boolean food;
    boolean projection;
    String notes;

    private MeetingRoomDTO meetingRoomDTO;
}
