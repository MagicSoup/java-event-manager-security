package com.event.manager.security;

import com.event.manager.security.config.DefaultSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;

@DefaultSpringBootTest
class EventManagerSecurityApplicationTests {

    @Value("${server.port}")
    private Integer serverPort;

    @Test
    void contextLoads() {
        assertThat(serverPort).isEqualTo(9000);
    }
}
