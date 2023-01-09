package com.event.manager.security.domain.api;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class RoleDTO implements Serializable {
    private final String name;
    private final Set<String> permissions;
}
