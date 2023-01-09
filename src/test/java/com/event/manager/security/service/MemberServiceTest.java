package com.event.manager.security.service;

import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import com.event.manager.security.domain.model.Permission;
import com.event.manager.security.domain.model.api.MemberDTO;
import com.event.manager.security.mapper.MemberMapper;
import com.event.manager.security.service.das.MemberDAS;
import com.event.manager.security.service.das.mock.MemberRolePermissionMockDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberDAS memberDASMocked;

    private MemberMapper memberMapper;

    private MemberService underTest;

    @BeforeEach
    public void init() {
        this.memberMapper = new MemberMapper();
        this.underTest = new MemberService(this.memberDASMocked, this.memberMapper);
    }

    @Test
    void getByUsername_givenHappyUsername_ThenReturnMemberDTO() throws MemberNotFoundException {
        // params
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
        when(memberDASMocked.getByUsername(usernamedExpected)).thenReturn(this.memberMapper.map(provider.mockResultForMemberRolePermission()));

        // execute
        MemberDTO memberDTO = this.underTest.getByUsername(usernamedExpected);

        // assert
        assertThat(memberDTO).isNotNull();
        assertThat(memberDTO.getUsername()).isEqualTo(usernamedExpected);
        assertThat(memberDTO.getEmail()).isEqualTo(emailExpected);
        assertThat(memberDTO.getPermissions()).isNotEmpty();
        String permissionKey = String.format("%s_%S", applicationExpected, roleExpected);
        assertThat(memberDTO.getPermissions().get(permissionKey)).containsExactly(Permission.VIEW);

        // verify
        verify(this.memberDASMocked).getByUsername(usernamedExpected);
        verifyNoMoreInteractions(this.memberDASMocked);
    }
}