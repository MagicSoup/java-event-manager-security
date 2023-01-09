package com.event.manager.security.mapper;

import com.event.manager.security.domain.model.Role;
import com.event.manager.security.domain.model.api.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDTO map(Role role) {
        return new RoleDTO(role.name(), role.getPermissions());
    }
}
