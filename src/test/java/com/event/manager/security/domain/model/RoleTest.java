package com.event.manager.security.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.event.manager.security.domain.model.Permission.*;
import static com.event.manager.security.domain.model.Role.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {

    private static Stream<Arguments> getPermissionsArgumentProvider() {
        return Stream.of(
                Arguments.of(SUPER_ADMIN, new HashSet<>(asList(MANAGE_ALL, MANAGE, CREATE, UPDATE, DELETE, VIEW))),
                Arguments.of(ADMIN, new HashSet<>(asList(MANAGE, CREATE, UPDATE, DELETE, VIEW))),
                Arguments.of(ORGANIZER, new HashSet<>(asList(CREATE, UPDATE, DELETE, VIEW))),
                Arguments.of(GUESS, new HashSet<>(asList(VIEW))),
                Arguments.of(NONE, Collections.emptySet())
        );
    }

    @ParameterizedTest
    @MethodSource("getPermissionsArgumentProvider")
    void getPermissions(Role role, Set<Permission> expectedPermissions) {
        assertThat(role.getPermissions())
                .containsExactlyInAnyOrderElementsOf(expectedPermissions);
    }

    @Test
    void values() {
        // execute && assert
        assertThat(Role.values())
                .containsExactly(SUPER_ADMIN, ADMIN, ORGANIZER, GUESS, NONE);
    }
}