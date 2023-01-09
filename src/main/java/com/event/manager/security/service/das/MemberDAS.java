package com.event.manager.security.service.das;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;

/**
 * The interface Author das.
 */
public interface MemberDAS {

    /**
     * Gets by username.
     *
     * @param username the member username
     * @return the member
     */
    MemberRecord getByUsername(String username) throws MemberNotFoundException;
}
