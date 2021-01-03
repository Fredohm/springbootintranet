package org.fredohm.springbootintranet.config.permissions.meetingRoom;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('meetingRoom.update')")
public @interface UpdateMeetingRoom {
}
