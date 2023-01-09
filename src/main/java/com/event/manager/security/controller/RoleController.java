package com.event.manager.security.controller;

import com.event.manager.security.domain.model.api.RoleDTO;
import com.event.manager.security.domain.exception.notfound.RoleNotFoundException;
import com.event.manager.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Role Controller
 */
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Get a specific role
     *
     * @param role the role to retrieve
     * @return the Role
     * @throws RoleNotFoundException if the specified role doesn't exist
     */
    @GetMapping("/{role}")
    public RoleDTO get(@PathVariable(value = "role") String role) throws RoleNotFoundException {
        return this.roleService.get(role);
    }

    /**
     * Gets all roles
     *
     * @return all the roles
     */
    @GetMapping
    public Set<RoleDTO> getAll() {
        return this.roleService.getAll();
    }
}
