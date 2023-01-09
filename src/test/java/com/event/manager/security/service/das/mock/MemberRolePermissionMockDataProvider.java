package com.event.manager.security.service.das.mock;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.db.security.tables.records.MemberRolePermissionRecord;
import com.event.manager.db.security.tables.records.MemberRoleRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.event.manager.db.security.Tables.MEMBER_ROLE_PERMISSION;
import static com.event.manager.db.security.tables.Member.MEMBER;
import static com.event.manager.db.security.tables.MemberRole.MEMBER_ROLE;

public class MemberRolePermissionMockDataProvider implements MockDataProvider {

    private final String username;
    private final String password;
    private final String email;
    private final LocalDateTime createdOn;
    private final String application;

    private final String role;

    private final String permission;

    public MemberRolePermissionMockDataProvider(String username, String password, String email, LocalDateTime createdOn,
                                                String application, String role, String permission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdOn = createdOn;
        this.application = application;
        this.role = role;
        this.permission = permission;
    }

    @Override
    public MockResult[] execute(MockExecuteContext mockExecuteContext) throws SQLException {
        DSLContext create = DSL.using(SQLDialect.H2);
        MockResult[] mock = new MockResult[1];

        // Always return one record
        Result<Record12<Integer, String, String, String, LocalDateTime, Integer, Integer, String, String, Integer, Integer, String>> result = create
                .newResult(
                        MEMBER.ID, MEMBER.USERNAME, MEMBER.PASSWORD, MEMBER.EMAIL, MEMBER.CREATED_ON, // member
                        MEMBER_ROLE.ID, MEMBER_ROLE.MEMBER_ID, MEMBER_ROLE.APPLICATION, MEMBER_ROLE.ROLE, // member role
                        MEMBER_ROLE_PERMISSION.ID, MEMBER_ROLE_PERMISSION.MEMBER_ROLE_ID, MEMBER_ROLE_PERMISSION.PERMISSION // member role permission
                );
        result.add(create
                .newRecord(MEMBER.ID, MEMBER.USERNAME, MEMBER.EMAIL, MEMBER.PASSWORD, MEMBER.CREATED_ON, // member
                        MEMBER_ROLE.ID, MEMBER_ROLE.MEMBER_ID, MEMBER_ROLE.APPLICATION, MEMBER_ROLE.ROLE, // member role
                        MEMBER_ROLE_PERMISSION.ID, MEMBER_ROLE_PERMISSION.MEMBER_ROLE_ID, MEMBER_ROLE_PERMISSION.PERMISSION // member role permission
                )
                .values(1, this.username, this.password, this.email, this.createdOn, // member data
                        1, 1, this.application, this.role, // member role data
                        1, 1, this.permission // member role permission data
                )
        );
        mock[0] = new MockResult(1, result);

        return mock;
    }

    public Result<Record3<MemberRecord, MemberRoleRecord, MemberRolePermissionRecord>> mockResultForMemberRolePermission() {
        MockConnection connection = new MockConnection(this);
        // Pass the mock connection to a jOOQ DSLContext:
        DSLContext create = DSL.using(connection, SQLDialect.H2);
        // Execute queries transparently, with the above DSLContext:
        return create.select(MEMBER, MEMBER_ROLE, MEMBER_ROLE_PERMISSION)
                .from(MEMBER)
                .innerJoin(MEMBER_ROLE).onKey()
                .leftOuterJoin(MEMBER_ROLE_PERMISSION).onKey()
                .where(MEMBER.ID.eq(1))
                .fetch();
    }
}
