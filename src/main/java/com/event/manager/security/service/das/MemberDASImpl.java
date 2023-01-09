package com.event.manager.security.service.das;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.db.security.tables.records.MemberRolePermissionRecord;
import com.event.manager.db.security.tables.records.MemberRoleRecord;
import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import com.event.manager.security.domain.model.entity.MemberEntity;
import com.event.manager.security.mapper.MemberMapper;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.event.manager.db.security.tables.Member.MEMBER;
import static com.event.manager.db.security.tables.MemberRole.MEMBER_ROLE;
import static com.event.manager.db.security.tables.MemberRolePermission.MEMBER_ROLE_PERMISSION;
import static java.util.Objects.requireNonNull;

/**
 * The type Author das.
 */
@Repository
public class MemberDASImpl implements MemberDAS {

    private final DSLContext dsl;

    private final MemberMapper memberMapper;

    /**
     * Instantiates a new Member das.
     *
     * @param dsl          the dsl
     * @param memberMapper the member mapper
     */
    @Autowired
    public MemberDASImpl(DSLContext dsl, MemberMapper memberMapper) {
        this.dsl = requireNonNull(dsl, "The DSLContext is required");
        this.memberMapper = requireNonNull(memberMapper, "The memberMapper is required");
    }

    @Override
    public MemberEntity getByUsername(String username) throws MemberNotFoundException {
        Result<Record3<MemberRecord, MemberRoleRecord, MemberRolePermissionRecord>> recordResult = this.dsl.select(MEMBER, MEMBER_ROLE, MEMBER_ROLE_PERMISSION)
                .from(MEMBER)
                .innerJoin(MEMBER_ROLE).onKey()
                .leftOuterJoin(MEMBER_ROLE_PERMISSION).onKey()
                .where(DSL.trueCondition())
                .and(MEMBER.USERNAME.eq(username))
                .fetch();

        MemberEntity memberEntity = this.memberMapper.map(recordResult);
        if (memberEntity == null) {
            throw new MemberNotFoundException(username);
        }

        return memberEntity;
    }
}
