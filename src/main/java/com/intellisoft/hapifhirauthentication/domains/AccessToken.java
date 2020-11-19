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
package com.intellisoft.hapifhirauthentication.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {

    private String access_token;

    private String token_type;

    private String refresh_token;

    private Long expires_in;

    private String scope;

    private String jti;

}
