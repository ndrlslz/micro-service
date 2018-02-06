package com.test.configuration;

import org.springframework.boot.autoconfigure.security.SecurityPrerequisite;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties(prefix = "api.security")
public class ApiSecurityProperties implements SecurityPrerequisite {
    private String[] paths = new String[]{"/**"};
    private String[] roles = new String[]{"ADMIN"};
    private Properties users = new Properties();

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public Properties getUsers() {
        return users;
    }

    public void setUsers(Properties users) {
        this.users = users;
    }
}
