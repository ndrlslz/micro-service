package com.test.configuration;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
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
        return new RestTemplate(factory());
    }

    public ClientHttpRequestFactory factory() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(2000)
                .setSocketTimeout(5000)
                .build();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
                .setMaxConnTotal(20)
                .setMaxConnPerRoute(20)
                .setDefaultRequestConfig(requestConfig);

        return new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());
    }
}
