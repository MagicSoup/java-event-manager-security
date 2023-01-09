package com.event.manager.security.mapper;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.security.domain.api.MemberDTO;
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
        String usernamedExpected = "usernamedExpected";
        String passwordExpected = "passwordExpected";
        String emailExpected = "emailExpected";
        MemberRecord memberRecord = new MemberRecord(idExpected, usernamedExpected, passwordExpected, emailExpected, LocalDateTime.now());

        // execute
        MemberDTO memberDTO = this.underTest.map(memberRecord);

        // assert
        assertThat(memberDTO).isNotNull();
        assertThat(memberDTO.getUserName()).isEqualTo(usernamedExpected);
        assertThat(memberDTO.getEmail()).isEqualTo(emailExpected);

    }
}