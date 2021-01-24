package org.fredohm.springbootintranet.exceptions;

public class AlreadyBookedException extends RuntimeException {

    public AlreadyBookedException() {
        super();
    }

    public AlreadyBookedException(String message) {
        super(message);
    }

    public AlreadyBookedException(String message, Throwable cause) {
        super(message, cause);
    }
}
