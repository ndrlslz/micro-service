package com.test.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceInstanceController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("client/{client}")
    public List<ServiceInstance> retrieveClientInfo(@PathVariable String client) {
        return discoveryClient.getInstances(client);
    }
}
