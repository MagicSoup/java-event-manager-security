package com.event.manager.security.mapper;

import com.event.manager.db.security.tables.records.MemberRecord;
import com.event.manager.db.security.tables.records.MemberRolePermissionRecord;
import com.event.manager.db.security.tables.records.MemberRoleRecord;
import com.event.manager.security.domain.model.Permission;
import com.event.manager.security.domain.model.api.MemberDTO;
import com.event.manager.security.domain.model.entity.MemberEntity;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemberMapper {
    public MemberDTO map(MemberEntity memberEntity) {
        return new MemberDTO(memberEntity.getUsername(), memberEntity.getEmail()).setPermissions(memberEntity.getPermissions());
    }

    @Nullable
    public MemberEntity map(Result<Record3<MemberRecord, MemberRoleRecord, MemberRolePermissionRecord>> recordResult) {

        MemberEntity memberEntity = null;
        Map<String, List<Permission>> permissionsMap = new HashMap<>();

        for (Record3<MemberRecord, MemberRoleRecord, MemberRolePermissionRecord> recordFromResult : recordResult) {
            if (memberEntity == null) {
                MemberRecord memberRecord = recordFromResult.value1();
                memberEntity = mapMemberRecordToMemberEntity(memberRecord, permissionsMap);
            }

            MemberRoleRecord memberRoleRecord = recordFromResult.value2();
            String applicationRole = String.format("%s_%s", memberRoleRecord.getApplication(), memberRoleRecord.getRole());
            List<Permission> permissions = permissionsMap.computeIfAbsent(applicationRole, k -> new ArrayList<>());
            MemberRolePermissionRecord memberRolePermissionRecord = recordFromResult.value3();
            if (memberRolePermissionRecord.getPermission() != null) {
                permissions.add(Permission.valueOf(memberRolePermissionRecord.getPermission()));
            }
        }

        return memberEntity;
    }

    private MemberEntity mapMemberRecordToMemberEntity(MemberRecord memberRecord, Map<String, List<Permission>> permissionsMap) {
        return new MemberEntity(memberRecord.getId(), memberRecord.getUsername(), memberRecord.getEmail(), permissionsMap);
    }
}