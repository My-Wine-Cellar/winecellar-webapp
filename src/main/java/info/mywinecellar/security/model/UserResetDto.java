/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.model;

import info.mywinecellar.security.password.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserResetDto {

    @NotNull
    @NotEmpty(message = "{validation.username.notEmpty}")
    @Size(max = 255, message = "{validation.username.size}")
    private String userName;

    @ValidPassword
    private String password;

    private String matchingPassword;
}
