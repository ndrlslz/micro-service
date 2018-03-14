package com.test.bff.utils;

import org.springframework.web.util.UriComponentsBuilder;

public class UrlBuilder {
    private static final String RETRIEVE_ORDERS_URL = "{baseUrl}/shops/{shopId}/orders";
    private static final String RETRIEVE_VEHICLES_URL = "{baseUrl}/vehicles";
    private String baseUrl;

    private UrlBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static UrlBuilder newUrlBuilder(String baseUrl) {
        return new UrlBuilder(baseUrl);
    }

    public String buildRetrieveOrdersUrl(String shopId) {
        return UriComponentsBuilder.fromPath(RETRIEVE_ORDERS_URL)
                .buildAndExpand(baseUrl, shopId)
                .toUriString();
    }

    public String buildRetrieveVehiclesUrl(String shopId) {
        return UriComponentsBuilder.fromPath(RETRIEVE_VEHICLES_URL)
                .queryParam("shopId", shopId)
                .buildAndExpand(baseUrl)
                .toUriString();
    }
}
