package com.event.manager.security.service.das;

import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import com.event.manager.security.domain.model.entity.MemberEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.of;

@ActiveProfiles("test")
@SpringBootTest
class MemberDASImplIntegrationTest {

    @Autowired
    private MemberDAS memberDAS;

    private static Stream<Arguments> getRolesArgumentProvider() {
        return Stream.of(
                of("MickeyMouse", 1, 3),
                of("PeterPan", 2, 2),
                of("MadHatter", 3, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("getRolesArgumentProvider")
    void getByUsername_givenHappyUsername_ThenReturnMemberFromDatabase(String username, Integer idExpected, Integer applicationNumberExpected) throws MemberNotFoundException {

        // execute
        MemberEntity memberEntity = this.memberDAS.getByUsername(username);

        // assert
        assertThat(memberEntity).isNotNull();
        assertThat(memberEntity.getId()).isEqualTo(idExpected);
        assertThat(memberEntity.getUsername()).isEqualTo(username);
        assertThat(memberEntity.getEmail()).isEqualTo(format("%s@EVENT-MANAGER.COM", username));
        assertThat(memberEntity.getPermissions()).hasSize(applicationNumberExpected);
    }

    @Test
    void getByUsername_givenUnknownUsername_ThenThrowMemberNotFoundException() {

        // params
        String username = "Tinkerbell";

        // execute && assert
        assertThatThrownBy(() -> this.memberDAS.getByUsername(username))
                .isExactlyInstanceOf(MemberNotFoundException.class)
                .hasMessage(format("The asked member %s has been not found", username));
    }
}