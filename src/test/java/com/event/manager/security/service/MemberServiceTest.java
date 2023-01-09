package com.event.manager.security.service;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.security.domain.api.MemberDTO;
import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import com.event.manager.security.mapper.MemberMapper;
import com.event.manager.security.service.das.MemberDAS;
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

    private MemberService underTest;

    @BeforeEach
    public void init() {
        this.underTest = new MemberService(this.memberDASMocked, new MemberMapper());
    }

    @Test
    void getByUsername_givenHappyUsername_ThenReturnMemberDTO() throws MemberNotFoundException {
        // params
        Integer idExpected = 1;
        String usernamedExpected = "usernamedExpected";
        String passwordExpected = "passwordExpected";
        String emailExpected = "emailExpected";
        MemberRecord memberRecord = new MemberRecord(idExpected, usernamedExpected, passwordExpected, emailExpected, LocalDateTime.now());

        // mock
        when(this.memberDASMocked.getByUsername(usernamedExpected)).thenReturn(memberRecord);

        // execute
        MemberDTO memberDTO = this.underTest.getByUsername(usernamedExpected);

        // assert
        assertThat(memberDTO).isNotNull();
        assertThat(memberDTO.getUserName()).isEqualTo(usernamedExpected);
        assertThat(memberDTO.getEmail()).isEqualTo(emailExpected);

        // verify
        verify(this.memberDASMocked).getByUsername(usernamedExpected);
        verifyNoMoreInteractions(this.memberDASMocked);
    }
}