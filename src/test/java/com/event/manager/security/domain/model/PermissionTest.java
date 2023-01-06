package com.event.manager.security.domain.model;

import org.junit.jupiter.api.Test;

import static com.event.manager.security.domain.model.Permission.*;
import static org.assertj.core.api.Assertions.assertThat;

class PermissionTest {

    @Test
    void values() {
        // execute && assert
        assertThat(Permission.values())
                .containsExactly(MANAGE_ALL, MANAGE, CREATE, UPDATE, DELETE, VIEW);
    }
}