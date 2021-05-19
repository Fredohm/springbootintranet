package org.fredohm.springbootintranet.model;

import lombok.*;

import javax.persistence.OrderBy;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingRoomDTO extends BaseDTO {

    static final long serialVersionUID = -3950371084128873179L;

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
