package com.test.bff.util;

import com.github.tomakehurst.wiremock.client.MappingBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.google.common.collect.ImmutableBiMap.of;
import static com.google.common.io.ByteStreams.toByteArray;
import static java.lang.String.format;

public class WireMockStub {
    public static void stubGet(String uriPath, String service, int statusCode, String jsonFile) throws IOException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(format("stub/%s/%s", service, jsonFile));
        stubFor(get(urlEqualTo(uriPath))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(toByteArray(is))));
    }

    public static void stubGet(String urlPath, Map<String, String> searchParameters, String service, int statusCode, String jsonFile) throws IOException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(format("stub/%s/%s", service, jsonFile));

        MappingBuilder builder = get(urlMatching(urlPath));
        searchParameters.forEach((key, value) -> builder.withQueryParam(key, equalTo(value)));
        stubFor(builder
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(toByteArray(is))));
    }

    public static void stubGet(String urlPath, String paramKey, String paramValue, String service, int statusCode, String jsonFile) throws IOException {
        stubGet(urlPath, of(paramKey, paramValue), service, statusCode, jsonFile);
    }
}
