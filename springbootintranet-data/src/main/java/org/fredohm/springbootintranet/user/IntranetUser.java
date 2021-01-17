package org.fredohm.springbootintranet.user;

import lombok.*;
import org.fredohm.springbootintranet.validation.PasswordMatch;
import org.fredohm.springbootintranet.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatch.List({
        @PasswordMatch(password = "password", confirmedPassword = "matchingPassword", message = "Must match!")
})
public class IntranetUser {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 12)
    private String username;

    @NotNull
    @Size(min = 5)
    private String password;

    @NotNull
    @Size(min = 5)
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

    private String formRole;
}
