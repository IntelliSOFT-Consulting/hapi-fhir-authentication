package com.intellisoft.hapifhirauthentication.service;

import com.intellisoft.hapifhirauthentication.domains.AccessToken;
import com.intellisoft.hapifhirauthentication.domains.KeycloakUser;
import com.intellisoft.hapifhirauthentication.exceptions.NotAuthorizedException;

public interface AuthenticationService {

    AccessToken login(KeycloakUser user) throws NotAuthorizedException;

    String user(String token) throws NotAuthorizedException;
}
