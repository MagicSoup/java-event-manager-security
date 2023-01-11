package com.event.manager.security.annotations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableAutoConfiguration
public @interface DefaultSpringBootTest {
    @AliasFor(annotation = ActiveProfiles.class, attribute = "profiles") String[] activeProfile() default {"test"};
    @AliasFor(annotation = SpringBootTest.class, attribute = "webEnvironment") SpringBootTest.WebEnvironment webEnvironment() default SpringBootTest.WebEnvironment.NONE;
    @AliasFor(annotation = SpringBootTest.class, attribute = "properties") String[] properties() default {};
    @AliasFor(annotation = EnableAutoConfiguration.class, attribute = "exclude") Class<?>[] exclude() default {};
}
