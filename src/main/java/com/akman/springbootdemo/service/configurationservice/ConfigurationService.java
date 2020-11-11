package com.akman.springbootdemo.service.configurationservice;

import com.akman.springbootdemo.model.apiauthorization.ApiAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConfigurationService {

    private final ApiAuthorization apiAuthorization;

    /**
     * This method provides Authenticated user class.
     * @return User
     */
    public ApiAuthorization.User getCurrentUser() {
        return apiAuthorization.getUsers().get(getCurrentUserName());
    }

    /**
     * This method provides authenticated username.
     * @return username
     */
    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
