package com.event.manager.security.domain.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberDTO implements Serializable {
    private final String userName;
    private final String email;
}
