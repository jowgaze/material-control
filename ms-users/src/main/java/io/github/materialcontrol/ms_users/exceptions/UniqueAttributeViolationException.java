package io.github.materialcontrol.ms_users.exceptions;

public class UniqueAttributeViolationException extends RuntimeException {
    public UniqueAttributeViolationException(String message) {
        super(message);
    }
}
