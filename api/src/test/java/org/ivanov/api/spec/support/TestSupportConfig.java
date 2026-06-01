package org.ivanov.api.spec.support;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration(proxyBeanMethods = false)
@ComponentScan(basePackages = "org.ivanov.api.environment.config.testcontainer")
public class TestSupportConfig {

}
