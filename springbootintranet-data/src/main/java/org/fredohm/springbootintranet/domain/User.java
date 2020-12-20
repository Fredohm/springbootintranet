package org.fredohm.springbootintranet.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Builder
    public User(Long id, String username/*, String password*/, String firstName, String lastName,
                String email, Set<Meeting> meetings) {
        super(id);
        this.username = username;
        //this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.meetings = meetings;
    }

    @NotBlank
    @Size(min = 4, max = 12)
    @Column(name = "username")
    private String username;
/*
    @Column(name = "password")
    private String password;
*/
    @NotBlank
    @Size(min = 1, max = 64)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 64)
    @Column(name = "last_name")
    private String lastName;

    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Meeting> meetings = new HashSet<>();

}
