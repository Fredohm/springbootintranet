package org.fredohm.springbootintranet.model;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class RoleDTO {

    @Builder
    public RoleDTO(Long id, String name, Set<AuthorityDTO> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    private Long id;

    private String name;

    private Set<AuthorityDTO> authorities;
}
