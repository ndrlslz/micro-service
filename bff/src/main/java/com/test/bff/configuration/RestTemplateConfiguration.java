package com.test.bff.configuration;

import com.test.utils.log.LoggingRequestAndResponseInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

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
    public RestTemplate restTemplate() {
        return buildRestTemplate();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplateV2() {
        return buildRestTemplate();
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

    private RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(factory());
        ArrayList<ClientHttpRequestInterceptor> interceptors = newArrayList();
        interceptors.add(new LoggingRequestAndResponseInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
