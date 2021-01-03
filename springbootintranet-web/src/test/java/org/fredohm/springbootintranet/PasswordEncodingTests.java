package org.fredohm.springbootintranet;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodingTests {

    static final String ADMIN = "admin";
    static final String MANAGER = "manager";
    static final String USER = "user";

    @Test
    void testBCrypt() {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        System.out.println(bcrypt.encode(ADMIN));
        System.out.println(bcrypt.encode(MANAGER));
        System.out.println(bcrypt.encode(USER));
    }

}
