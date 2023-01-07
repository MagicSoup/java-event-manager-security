package com.event.manager.security.service;

import com.event.manager.security.domain.exception.RoleNotFoundException;
import com.event.manager.security.domain.model.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    public Set<Role> getAll() {
        return Arrays.stream(Role.values())
                .collect(Collectors.toSet());
    }

    public Role get(String role) throws RoleNotFoundException {
        try {
            return Role.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new RoleNotFoundException(role);
        }
    }
}
