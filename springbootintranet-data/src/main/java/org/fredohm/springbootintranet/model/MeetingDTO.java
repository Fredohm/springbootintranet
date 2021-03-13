package org.fredohm.springbootintranet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {

    Long id;
    String title;
    String contact;
    Integer membersNb;
    LocalDate date;
    LocalTime start;
    LocalTime end;
    Boolean drinks;
    Boolean food;
    Boolean projection;
    String notes;
}
