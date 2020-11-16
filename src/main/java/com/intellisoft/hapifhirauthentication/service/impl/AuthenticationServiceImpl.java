package com.intellisoft.hapifhirauthentication.service.impl;

import com.intellisoft.hapifhirauthentication.domains.KeycloakUser;
import com.intellisoft.hapifhirauthentication.exceptions.NotAuthorizedException;
import com.intellisoft.hapifhirauthentication.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
@EnableAutoConfiguration
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${keycloak.url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client_id}")
    private String keycloakClientId;

    RestTemplate restTemplate = new RestTemplate();

    private static final String BEARER = "BEARER ";

    @Override
    public com.intellisoft.hapifhirauthentication.domains.AccessToken login(KeycloakUser user) throws NotAuthorizedException {
        try {
            String uri = keycloakUrl + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";
            String data = "grant_type=password&username=" + user.getUsername() +
                    "&password=" + user.getPassword() + "&client_id=" + keycloakClientId;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            HttpEntity<String> entity = new HttpEntity<>(data, headers);
            ResponseEntity<com.intellisoft.hapifhirauthentication.domains.AccessToken> response =
                    restTemplate.exchange(uri, HttpMethod.POST, entity, com.intellisoft.hapifhirauthentication.domains.AccessToken.class);

            if (response.getStatusCode()
                    .value() != HttpStatus.SC_OK) {
                log.error("Unauthorised access to protected resource. status code: " + response.getStatusCode().value());
                throw new NotAuthorizedException("Unauthorised access to protected resource");
            }
            return response.getBody();
        } catch (Exception ex) {
            log.error("Unauthorised access to protected resource", ex);
            throw new NotAuthorizedException("Unauthorised access to protected resource");
        }
    }

    @Override
    public String user(String authToken) throws NotAuthorizedException {

        if (!authToken.toUpperCase()
                .startsWith(BEARER)) {
            throw new NotAuthorizedException("Invalid OAuth Header. Missing Bearer prefix");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<AccessToken> response = restTemplate.exchange(keycloakUrl +
                "/realms/" + keycloakRealm + "/protocol/openid-connect/userinfo", HttpMethod.POST, entity, AccessToken.class);

        if (response.getStatusCode()
                .value() != HttpStatus.SC_OK) {
            log.error("OAuth2 Authentication failure. " +
                            "Invalid OAuth Token supplied in Authorization Header on Request. Code {}",
                    response.getStatusCode().value());
            throw new NotAuthorizedException("OAuth2 Authentication failure. " +
                    "Invalid OAuth Token supplied in Authorization Header on Request.");
        }

        log.debug("User info: {}", Objects.requireNonNull(response.getBody()).getPreferredUsername());
        return response.getBody().getPreferredUsername();
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}