package com.fullcycle.admin.catalogo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;


@ActiveProfiles("test-integration")
@ExtendWith(MySQLCleanUpExtension.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ComponentScan(
        basePackages = "com.fullcycle.admin.catalogo",
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX,
                pattern = ".*MySQLGateway")})
@DataJpaTest
@ExtendWith(MySQLCleanUpExtension.class)
public @interface MySQLGatewayTest {
}
