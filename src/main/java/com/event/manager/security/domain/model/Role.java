package com.event.manager.security.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.event.manager.security.domain.model.Permission.*;
import static java.util.Arrays.asList;

public enum Role {
    SUPER_ADMIN(new HashSet<>(asList(MANAGE_ALL, MANAGE, CREATE, UPDATE, DELETE, VIEW))),
    ADMIN(new HashSet<>(asList(MANAGE, CREATE, UPDATE, DELETE, VIEW))),
    ORGANIZER(new HashSet<>(asList(CREATE, UPDATE, DELETE, VIEW))),
    GUESS(new HashSet<>(asList(VIEW))),
    NONE(Collections.emptySet());

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
