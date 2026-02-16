package com.sprk.security_oauth.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String user(OAuth2AuthenticationToken token) {

        return "Hello " + token.getPrincipal().getAttribute("name");
    }
}
