package com.event.manager.security.service;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.security.domain.api.MemberDTO;
import com.event.manager.security.domain.exception.notfound.MemberNotFoundException;
import com.event.manager.security.mapper.MemberMapper;
import com.event.manager.security.service.das.MemberDAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
public class MemberService {

    private final MemberDAS memberDAS;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberDAS memberDAS, MemberMapper memberMapper) {
        this.memberDAS = requireNonNull(memberDAS, "The memberDAS is required");
        this.memberMapper = requireNonNull(memberMapper, "The memberMapper is required");
    }

    public MemberDTO getByUsername(String username) throws MemberNotFoundException {
        MemberRecord memberRecord = this.memberDAS.getByUsername(username);
        return this.memberMapper.map(memberRecord);
    }
}
