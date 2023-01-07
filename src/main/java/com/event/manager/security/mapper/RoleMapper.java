package com.event.manager.security.mapper;

import com.event.manager.security.domain.api.RoleDTO;
import com.event.manager.security.domain.model.Permission;
import com.event.manager.security.domain.model.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleDTO map(Role role) {
        return new RoleDTO(role.name(),
                role.getPermissions().stream()
                        .map(Permission::name)
                        .collect(Collectors.toSet()));
    }
}
