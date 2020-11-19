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
package com.intellisoft.hapifhirauthentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotAuthorizedException(String message) {
        super(message);
    }
}
