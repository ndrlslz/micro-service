package com.test.util;

import java.io.IOException;
import java.io.InputStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.google.common.io.ByteStreams.toByteArray;
import static java.lang.String.format;

public class WiremockStub {
    public static void stubGet(String uriPath, String service, int statusCode, String jsonFile) throws IOException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(format("stub/%s/%s", service, jsonFile));
        stubFor(get(urlEqualTo(uriPath))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withBody(toByteArray(is))));
    }
}
