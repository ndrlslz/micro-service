package com.test.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "service.order")
    public RestEndpointProperties serviceOrderProperties() {
        return new RestEndpointProperties();
    }

    @Bean
    @ConfigurationProperties("service.vehicle")
    public RestEndpointProperties serviceVehicleProperties() {
        return new RestEndpointProperties();
    }

    @Bean
    public RestTemplate orderRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(factory());
        return restTemplate;
    }

    public ClientHttpRequestFactory factory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        return factory;
    }
}
