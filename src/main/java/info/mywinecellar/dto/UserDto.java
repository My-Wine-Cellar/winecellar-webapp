/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.User;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for user
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class UserDto implements Serializable {

    private int id;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;

    /**
     * Constructor
     * @param u The user
     */
    public UserDto(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.firstName = u.getFirstName();
        this.middleName = u.getMiddleName();
        this.lastName = u.getLastName();
    }
}
