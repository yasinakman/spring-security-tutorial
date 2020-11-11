package com.akman.springbootdemo.rest_api.config;

import com.akman.springbootdemo.model.apiauthorization.ApiAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApiAuthorization apiAuthorization;

    /**
     * This method checks the access information of users.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/admin/login/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/sec-admin/login/**").hasRole("SECADMIN")
                .anyRequest().denyAll()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    /**
     * This method ensures that there are no obstacles to swagger-ui.
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    /**
     * This method allows users to login the system.
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> builder =
                auth.inMemoryAuthentication();
        Map<String, ApiAuthorization.User> users = apiAuthorization.getUsers();
        users.forEach((username, user) -> {
            builder.withUser(username).password(user.getPassword()).authorities(user.getConfig().getRoles());
        });
    }
}