package com.test.bff.configuration;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public class RestEndpointProperties {
    private static final int MINIMAL_TIMEOUT = 1000;
    @NotBlank
    private String url;

    private String username;
    private String password;

    @Min(MINIMAL_TIMEOUT)
    private int timeout;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
