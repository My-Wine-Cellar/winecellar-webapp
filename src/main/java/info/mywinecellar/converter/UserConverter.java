/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.converter;

import info.mywinecellar.dto.UserDto;
import info.mywinecellar.model.User;

import java.util.Optional;

/**
 * Utility class for {@link User} and {@link UserDto} conversion
 */
public final class UserConverter {

    private UserConverter() {
    }

    /**
     * Convert entity to dto
     *
     * @param user user
     * @return dto object
     */
    public static UserDto toDto(User user) {
        return Optional.ofNullable(user)
                .map(UserDto::new)
                .orElse(null);
    }
}
