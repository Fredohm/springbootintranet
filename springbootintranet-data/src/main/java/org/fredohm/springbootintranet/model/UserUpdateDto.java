package org.fredohm.springbootintranet.model;

import org.fredohm.springbootintranet.domain.security.Role;
import org.fredohm.springbootintranet.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserUpdateDto {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 12)
    private String username;

    @NotBlank
    @Size(min = 1, max = 64)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 64)
    private String lastName;

    @ValidEmail
    @NotNull
    private String email;

    private Role role;
}

