package com.event.manager.security.service.das;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.event.manager.db.security.tables.Member.MEMBER;
import static java.util.Objects.requireNonNull;

/**
 * The type Author das.
 */
@Repository
public class MemberDASImpl implements MemberDAS {

    private final DSLContext dsl;

    /**
     * Instantiates a new Member das.
     *
     * @param dsl the dsl
     */
    @Autowired
    public MemberDASImpl(DSLContext dsl) {
        this.dsl = requireNonNull(dsl, "The DSLContext is required");
    }

    @Override
    public MemberRecord getByUsername(String username) throws MemberNotFoundException {
        MemberRecord memberRecord = this.dsl.selectFrom(MEMBER).where(MEMBER.USERNAME.eq(username)).fetchOne();
        if (memberRecord == null) {
            throw new MemberNotFoundException(username);
        }
        return memberRecord;
    }
}
