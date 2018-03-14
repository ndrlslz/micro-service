package com.test.bdd.common;

import java.util.HashMap;
import java.util.Map;

public class CucumberRequest {
    private String path;
    private Map<String, String> pathParams = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();

    public String getPath() {
        return path;
    }

    private void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public void addPathParam(String key, String value) {
        pathParams.put(key, value);
    }

    public CucumberRequest withPathParam(String key, String value) {
        addPathParam(key, value);
        return this;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    private void addQueryParam(String key, String value) {
        queryParams.put(key, value);
    }

    public CucumberRequest withQueryParam(String key, String value) {
        addQueryParam(key, value);
        return this;
    }

    public CucumberRequest withPath(String path) {
        setPath(path);
        return this;
    }
}
