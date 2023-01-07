package com.event.manager.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EventManagerSecurityApplicationTests {

	@Value("${server.port}")
	private Integer serverPort;

	@Test
	void contextLoads() {
		assertThat(serverPort).isEqualTo(9000);
	}
}
