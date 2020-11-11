package com.akman.springbootdemo.model.apiauthorization;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "api.authorization")/** Get authorization infos in application.yaml.*/
@Component
public class ApiAuthorization {
    private Map<String, User> users = new HashMap<>();
    public Map<String, User> getUsers() {
        return users;
    }
    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
    /**
     * User class
     * */
    public static class User {
        private String password;
        private UserConfig config;

        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public UserConfig getConfig() {
            return config;
        }
        public void setConfig(UserConfig config) {
            this.config = config;
        }
    }
    /**
     * User config class
     * */
    public static class UserConfig {
        private boolean timeout;
        private String[] roles;
        public String[] getRoles() {
            return roles;
        }
        public void setRoles(String[] roles) {
            this.roles = roles;
        }
        public boolean isTimeout() {
            return timeout;
        }
        public void setTimeout(boolean timeout) {
            this.timeout = timeout;
        }
    }
}