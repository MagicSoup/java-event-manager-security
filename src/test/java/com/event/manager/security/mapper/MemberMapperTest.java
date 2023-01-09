package com.event.manager.security.mapper;

import com.event.manager.security.domain.model.Permission;
import com.event.manager.security.domain.model.entity.MemberEntity;
import com.event.manager.security.service.das.mock.MemberRolePermissionMockDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class MemberMapperTest {

    private MemberMapper underTest;

    @BeforeEach
    public void init() {
        this.underTest = new MemberMapper();
    }

    @Test
    void map() {

        // params
        Integer idExpected = 1;
        String usernamedExpected = "PeterPan";
        String passwordExpected = "PASSWORD";
        String emailExpected = "PeterPan@EVENT-MANAGER.COM";
        LocalDateTime createdOnExpected = LocalDateTime.now();
        String applicationExpected = "EVENT_MANAGER_BOARDING_GAME";
        String roleExpected = "GUESS";
        String permissionExpected = "VIEW";

        // mock
        MemberRolePermissionMockDataProvider provider = new MemberRolePermissionMockDataProvider(usernamedExpected, passwordExpected, emailExpected, createdOnExpected,
                applicationExpected, roleExpected, permissionExpected);

        // execute
        MemberEntity memberEntity = this.underTest.map(provider.mockResultForMemberRolePermission());

        // assert
        assertThat(memberEntity).isNotNull();
        assertThat(memberEntity.getId()).isEqualTo(idExpected);
        assertThat(memberEntity.getUsername()).isEqualTo(usernamedExpected);
        assertThat(memberEntity.getEmail()).isEqualTo(emailExpected);
        assertThat(memberEntity.getPermissions()).isNotEmpty();
        String permissionKey = String.format("%s_%S", applicationExpected, roleExpected);
        assertThat(memberEntity.getPermissions().get(permissionKey)).containsExactly(Permission.VIEW);
    }
}