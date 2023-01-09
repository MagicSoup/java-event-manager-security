package com.event.manager.security.domain.exception.notfound;

import com.event.manager.security.domain.exception.SecurityException;

import static java.lang.String.format;

public class MemberNotFoundException extends SecurityException {

    public static final String ERROR_MESSAGE = "The asked member %s has been not found";

    public MemberNotFoundException(String role) {
        super(format(ERROR_MESSAGE, role));
    }
}
