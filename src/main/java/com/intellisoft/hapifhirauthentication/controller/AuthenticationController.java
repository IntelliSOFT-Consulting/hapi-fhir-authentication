package com.intellisoft.hapifhirauthentication.controller;

import com.intellisoft.hapifhirauthentication.domains.AccessToken;
import com.intellisoft.hapifhirauthentication.domains.KeycloakUser;
import com.intellisoft.hapifhirauthentication.exceptions.NotAuthorizedException;
import com.intellisoft.hapifhirauthentication.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @ApiOperation(value = "Give OAuth access token given user and password")
    @PostMapping("/login")
    public AccessToken login(@RequestBody KeycloakUser user) throws NotAuthorizedException {
        log.info("Login user");
        return authenticationService.login(user);
    }

    @ApiOperation(value = "Provide the user preferred name given the token")
    @GetMapping("/user")
    public String user(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws NotAuthorizedException {
        log.info("Get User info");
        return authenticationService.user(token);
    }
}
