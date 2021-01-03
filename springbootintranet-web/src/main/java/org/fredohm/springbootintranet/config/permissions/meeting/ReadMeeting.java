package org.fredohm.springbootintranet.config.permissions.meeting;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('meeting.read')")
public @interface ReadMeeting {
}
