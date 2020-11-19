## hapi-fhir authentication
Authentication for a HAPI FHIR Server using keycloak

## Keycloak configuration
- First, you need a deployed instance of keycloak authorization server. Use [this guide](https://www.keycloak.org/getting-started/getting-started-docker) to deploy an instance using docker.
- Add a realm e.g `demo-realm`
- Create a new user, set a password
- Add a client under the realm you've created. For example `test-client`
- Create `.env` file and add the following environment variables
  ```json
  KEYCLOAK_URL=http://localhost:8081/auth
  KEYCLOAK_REALM=demo-realm
  KEYCLOAK_CLIENT_ID=test-client
  ```
