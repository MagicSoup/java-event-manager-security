package com.event.manager.security.mapper;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.security.domain.api.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberDTO map(MemberRecord memberRecord) {
        return new MemberDTO(memberRecord.getUsername(), memberRecord.getEmail());
    }
}
