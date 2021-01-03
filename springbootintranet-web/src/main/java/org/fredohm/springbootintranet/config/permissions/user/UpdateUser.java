package org.fredohm.springbootintranet.config.permissions.user;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('user.update')")
public @interface UpdateUser {
}
