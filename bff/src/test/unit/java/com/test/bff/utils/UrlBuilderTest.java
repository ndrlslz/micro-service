package com.test.bff.utils;

import com.test.bff.utils.UrlBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlBuilderTest {
    private static final String BASE_URL = "http://localhost:80";
    @Test
    public void buildRetrieveOrdersUrl() throws Exception {
        String expected = BASE_URL + "/shops/1/orders";
        String response = UrlBuilder.newUrlBuilder(BASE_URL).buildRetrieveOrdersUrl("1");

        assertEquals(expected, response);
    }

    @Test
    public void buildRetrieveVehiclesUrl() throws Exception {
        String expected = BASE_URL + "/vehicles?shopId=1";
        String response = UrlBuilder.newUrlBuilder(BASE_URL).buildRetrieveVehiclesUrl("1");

        assertEquals(expected, response);
    }

}