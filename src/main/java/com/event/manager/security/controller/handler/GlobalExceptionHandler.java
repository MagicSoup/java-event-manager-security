package com.event.manager.security.controller.handler;

import com.event.manager.security.domain.exception.notfound.RoleNotFoundException;
import com.event.manager.security.domain.api.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Role Not Found")
    @ExceptionHandler(RoleNotFoundException.class)
    public ApiError handleRoleNotFoundException(HttpServletRequest request, RoleNotFoundException ex) {
        return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURL().toString());
    }
}
