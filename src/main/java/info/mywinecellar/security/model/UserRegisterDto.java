/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.model;

import info.mywinecellar.util.Regex;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Validated
public class UserRegisterDto extends UserResetDto {

    @NotNull
    @NotEmpty(message = "{validation.firstName.notEmpty}")
    @Size(max = 255, message = "{validation.name.size}")
    private String firstName;

    @Size(max = 255, message = "{validation.name.size}")
    private String middleName;

    @NotNull
    @NotEmpty(message = "{validation.lastName.notEmpty}")
    @Size(max = 255, message = "{validation.name.size}")
    private String lastName;

    @Pattern(message = "{validation.email}", regexp = Regex.EMAIL_PATTERN)
    private String email;

    /**
     * @return firstName
     */
    public String getFirstName() {
        return StringUtils.capitalize(firstName);
    }

    /**
     * @return middleName
     */
    public String getMiddleName() {
        return StringUtils.isAllEmpty(middleName) ? "" : middleName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return StringUtils.capitalize(lastName);
    }
}
