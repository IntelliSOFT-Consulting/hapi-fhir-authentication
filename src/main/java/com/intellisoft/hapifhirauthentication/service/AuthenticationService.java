package com.intellisoft.hapifhirauthentication.service;

import com.intellisoft.hapifhirauthentication.domains.AccessToken;
import com.intellisoft.hapifhirauthentication.domains.KeycloakUser;
import com.intellisoft.hapifhirauthentication.exceptions.NotAuthorizedException;

public interface AuthenticationService {

    public AccessToken login(KeycloakUser user) throws NotAuthorizedException;

    public String user(String token) throws NotAuthorizedException;
}
