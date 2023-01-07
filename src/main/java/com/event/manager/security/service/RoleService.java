package com.event.manager.security.service;

import com.event.manager.security.domain.api.RoleDTO;
import com.event.manager.security.domain.exception.RoleNotFoundException;
import com.event.manager.security.domain.model.Role;
import com.event.manager.security.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public Set<RoleDTO> getAll() {
        return Arrays.stream(Role.values())
                .map(this.roleMapper::map)
                .collect(Collectors.toSet());
    }

    public RoleDTO get(String role) throws RoleNotFoundException {
        try {
            return this.roleMapper.map(Role.valueOf(role));
        } catch (IllegalArgumentException e) {
            throw new RoleNotFoundException(role);
        }
    }
}
