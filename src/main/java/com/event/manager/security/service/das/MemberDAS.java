package com.event.manager.security.service.das;

import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import com.event.manager.security.domain.model.entity.MemberEntity;

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
    MemberEntity getByUsername(String username) throws MemberNotFoundException;
}
