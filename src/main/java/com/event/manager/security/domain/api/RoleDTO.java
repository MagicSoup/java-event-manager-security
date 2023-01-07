package com.event.manager.security.domain.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class RoleDTO {
    private final String name;
    private final Set<String> permissions;
}
