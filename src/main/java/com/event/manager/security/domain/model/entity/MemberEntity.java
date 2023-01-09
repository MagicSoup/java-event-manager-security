package com.event.manager.security.domain.model.entity;

import com.event.manager.security.domain.model.Permission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class MemberEntity implements Serializable {
    private final Integer id;
    private final String username;
    private final String email;
    private final Map<String, List<Permission>> permissions;
}
