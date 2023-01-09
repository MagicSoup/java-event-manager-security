package com.event.manager.security.mapper;

import com.event.manager.security.domain.model.api.RoleDTO;
import com.event.manager.security.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.event.manager.security.domain.model.Role.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

class RoleMapperTest {

    private RoleMapper underTest;

    @BeforeEach
    public void init() {
        this.underTest = new RoleMapper();
    }

    private static Stream<Arguments> getRolesArgumentProvider() {
        return Stream.of(
                of(SUPER_ADMIN, 6),
                of(ADMIN, 5),
                of(ORGANIZER, 4),
                of(GUESS, 1),
                of(NONE, 0));
    }

    @ParameterizedTest
    @MethodSource("getRolesArgumentProvider")
    void map(Role role, int expectedNumberOfPermissions) {

        // execute
        RoleDTO roleDTO = this.underTest.map(role);

        //assert
        assertThat(roleDTO).isNotNull();
        assertThat(roleDTO.getName()).isEqualTo(role.name());
        assertThat(roleDTO.getPermissions())
                .hasSize(role.getPermissions().size())
                .hasSize(expectedNumberOfPermissions);
    }
}