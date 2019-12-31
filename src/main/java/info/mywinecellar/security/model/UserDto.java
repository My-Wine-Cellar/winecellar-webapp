/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.model;

import info.mywinecellar.security.password.ValidPassword;
import info.mywinecellar.util.Regex;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
public class UserDto {

    @NotNull
    @NotEmpty(message = "{validation.firstName.notEmpty}")
    @Size(max = 30, message = "{validation.name.size}")
    private String firstName;

    @NotNull
    @NotEmpty(message = "{validation.lastName.notEmpty}")
    @Size(max = 30, message = "{validation.name.size}")
    private String lastName;

    @NotNull
    @NotEmpty(message = "{validation.username.notEmpty}")
    @Size(min = 6, max = 30, message = "{validation.username.size}")
    private String userName;

    @ValidPassword
    private String password;

    private String matchingPassword;

    @Pattern(message = "{validation.email}", regexp = Regex.EMAIL_PATTERN)
    private String email;

    /**
     * @return firstName
     */
    public String getFirstName() {
        return StringUtils.capitalize(firstName);
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return StringUtils.capitalize(lastName);
    }
}
