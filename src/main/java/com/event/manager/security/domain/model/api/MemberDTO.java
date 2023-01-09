package com.event.manager.security.domain.model.api;

import com.event.manager.security.domain.model.Permission;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class MemberDTO implements Serializable {
    private final String username;
    private final String email;
    private Map<String, List<Permission>> permissions;
}
