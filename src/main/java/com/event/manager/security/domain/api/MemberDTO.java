package com.event.manager.security.domain.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MemberDTO {
    private final Integer id;
    private final String userName;
    private final String email;
}
