package org.fredohm.springbootintranet.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @Column(name = "username")
    private String username;
/*
    @Column(name = "password")
    private String password;
*/
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Meeting> meetings = new HashSet<>();

}
