package org.fredohm.springbootintranet.exceptions;

public class ExistingMeetingsException extends RuntimeException {

    public ExistingMeetingsException() {
        super();
    }

    public ExistingMeetingsException(String message) {
        super(message);
    }

    public ExistingMeetingsException(String message, Throwable cause) {
        super(message, cause);
    }
}
