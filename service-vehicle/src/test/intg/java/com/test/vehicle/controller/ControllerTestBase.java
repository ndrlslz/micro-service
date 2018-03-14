package com.test.vehicle.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClientImportSelector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.String.format;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ControllerTestBase implements InitializingBean {
    @LocalServerPort
    private String port;

    String prefix;

    @Override
    public void afterPropertiesSet() throws Exception {
        prefix = format("http://localhost:%s/", port);
    }
}
