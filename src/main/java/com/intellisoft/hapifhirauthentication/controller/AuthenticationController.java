/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See theGNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero
 * General Public License along with this program.
 */
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
@SuppressWarnings("unused")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @ApiOperation(value = "Give OAuth access token given user and password")
    @PostMapping("/login")
    public AccessToken login(@RequestBody KeycloakUser user) throws NotAuthorizedException {
        log.info("LoggingIn user...");
        return authenticationService.login(user);
    }

    @ApiOperation(value = "Provide the user preferred name given the token")
    @GetMapping("/user")
    public String user(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws NotAuthorizedException {
        log.info("Getting User info...");
        return authenticationService.user(token);
    }
}
