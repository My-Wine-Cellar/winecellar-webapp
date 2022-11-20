/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
public class UserRegisterDto extends UserResetDto {

    @NotEmpty
    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String middleName;

    @NotEmpty
    @Size(max = 255)
    private String lastName;

    @NotEmpty
    @Email
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
