package com.event.manager.security.service.das;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest
class MemberDASImplTest {

    @Autowired
    private MemberDAS memberDAS;

    @Test
    void getByUsername_givenHappyUsername_ThenReturnMemberFromDatabase() throws MemberNotFoundException {

        // params
        String username = "GUESS-USER";

        // execute
        MemberRecord memberRecord = this.memberDAS.getByUsername(username);

        // assert
        assertThat(memberRecord).isNotNull();
        assertThat(memberRecord.getId()).isEqualTo(1);
        assertThat(memberRecord.getUsername()).isEqualTo(username);
        assertThat(memberRecord.getEmail()).isEqualTo(format("%s@EVENT-MANAGER.COM", username));
    }

    @Test
    void getByUsername_givenUnknownUsername_ThenThrowMemberNotFoundException() {

        // params
        String username = "GUESS-WHO-DO-NOT-EXIST";

        // execute && assert
        assertThatThrownBy(() -> this.memberDAS.getByUsername(username))
                .isExactlyInstanceOf(MemberNotFoundException.class)
                .hasMessage(format("The asked member %s has been not found", username));
    }
}