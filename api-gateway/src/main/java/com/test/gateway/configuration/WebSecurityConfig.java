package com.test.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ApiSecurityProperties apiSecurityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(apiSecurityProperties.getPaths()).hasAnyRole(apiSecurityProperties.getRoles())
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Configuration
    public static class LocalUsersSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Bean
        public ApiSecurityProperties apiSecurityProperties() {
            return new ApiSecurityProperties();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService());
        }

        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            return new InMemoryUserDetailsManager(apiSecurityProperties().getUsers());
        }
    }

}
