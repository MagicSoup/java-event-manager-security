package com.event.manager.security.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@ActiveProfiles
public @interface DefaultSpringBootTest {
    @AliasFor(annotation = ActiveProfiles.class, attribute = "profiles") String[] activeProfile() default {"test"};

    @AliasFor(annotation = SpringBootTest.class, attribute = "webEnvironment") SpringBootTest.WebEnvironment webEnvironment() default SpringBootTest.WebEnvironment.NONE;
}
