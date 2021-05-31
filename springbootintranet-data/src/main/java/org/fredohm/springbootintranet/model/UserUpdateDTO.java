package org.fredohm.springbootintranet.model;

import lombok.*;
import org.fredohm.springbootintranet.validation.PasswordMatch;
import org.fredohm.springbootintranet.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch.List({
        @PasswordMatch(password = "password", confirmedPassword = "matchingPassword", message = "Must match!")
})
public class UserUpdateDTO extends BaseDTO {

    static final long serialVersionUID = 4013463579227569452L;

    @Builder
    public UserUpdateDTO(Long id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                   String username, String password, String matchingPassword, String firstName,
                   String lastName, String email, List<RoleDTO> roles) {
        super(id, version, createdDate, lastModifiedDate);
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    @NotBlank
    @Size(min = 4, max = 12)
    private String username;

    @Null
    private String password;

    @Null
    private String matchingPassword;

    @NotBlank
    @Size(min = 1, max = 64)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 64)
    private String lastName;

    @ValidEmail
    @NotNull
    private String email;

    private List<RoleDTO> roles;
}


