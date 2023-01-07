package com.event.manager.security.service;

import com.event.manager.security.domain.exception.RoleNotFoundException;
import com.event.manager.security.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.event.manager.security.domain.model.Role.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.of;

class RoleServiceTest {

    private RoleService underTest;

    @BeforeEach
    public void init() {
        this.underTest = new RoleService();
    }

    @Test
    void getAll_givenHappyPath_thenRetrieveAllDefaultRoles() {
        // execute
        Set<Role> roles = this.underTest.getAll();

        // assert
        assertThat(roles)
                .containsExactlyInAnyOrder(SUPER_ADMIN, ADMIN, ORGANIZER, GUESS, NONE);
    }

    private static Stream<Arguments> getRolesArgumentProvider() {
        return Stream.of(
                of("SUPER_ADMIN", SUPER_ADMIN),
                of("ADMIN", ADMIN),
                of("ORGANIZER", ORGANIZER),
                of("GUESS", GUESS),
                of("NONE", NONE));
    }

    @ParameterizedTest
    @MethodSource("getRolesArgumentProvider")
    void get_givenHappyPath_thenRetrieveRole(String roleAsString, Role expectedRole) throws RoleNotFoundException {
        // params

        // execute
        Role foundRole = this.underTest.get(roleAsString);

        // assert
        assertThat(foundRole).isEqualTo(expectedRole);
    }

    @Test
    void get_givenUnknownRole_thenThrowRoleNotFoundException() {
        // params
        String unknownRole = "UNKNOWN";

        // execute && assert
        assertThatThrownBy(() -> this.underTest.get(unknownRole))
                .isExactlyInstanceOf(RoleNotFoundException.class)
                .hasMessage("The asked role UNKNOWN has been not found");
    }
}