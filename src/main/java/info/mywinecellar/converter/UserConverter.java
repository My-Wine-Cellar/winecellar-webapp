/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.UserDto;
import info.mywinecellar.model.User;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    /**
     * Convert entity to dto
     *
     * @param user user
     * @return dto object
     */
    public UserDto toDto(User user) {
        if (user == null) {
            throw new IllegalStateException("User is null");
        }
        return new UserDto(user);
    }
}
