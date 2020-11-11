package com.akman.springbootdemo.rest_api.controller;

import com.akman.springbootdemo.service.configurationservice.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    private final ConfigurationService configurationService;
    /**
     * As configured in SecurityConfiguration class, this method allows just the primary admin users to login.
     * */
    @GetMapping(value = "/admin/login")
    public String adminLogin(){
        System.out.println(configurationService.getCurrentUserName());
        return configurationService.getCurrentUserName();
    }
    /**
     * As configured in SecurityConfiguration class, this method allows just the secondary admin users to login.
     * */
    @GetMapping(value = "/sec-admin/login")
    public String secAdminLogin(){
        System.out.println(configurationService.getCurrentUserName());
        return configurationService.getCurrentUserName();
    }
}