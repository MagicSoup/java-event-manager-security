package com.event.manager.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"test"})
@SpringBootTest
class EventManagerSecurityApplicationTests {

    @Value("${server.port}")
    private Integer serverPort;

    @Test
    void contextLoads() {
        assertThat(serverPort).isEqualTo(9000);
    }
}
