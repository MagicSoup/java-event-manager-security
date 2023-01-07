package com.event.manager.security.domain.exception;

import static java.lang.String.format;

public class RoleNotFoundException extends SecurityException {

    public static final String ERROR_MESSAGE = "The asked role %s has been not found";

    public RoleNotFoundException(String role) {
        super(format(ERROR_MESSAGE, role));
    }
}
