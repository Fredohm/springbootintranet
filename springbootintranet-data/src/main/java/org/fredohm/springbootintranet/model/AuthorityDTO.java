package org.fredohm.springbootintranet.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class AuthorityDTO {

    @Builder
    public AuthorityDTO(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    private Long id;
    private String role;
}
