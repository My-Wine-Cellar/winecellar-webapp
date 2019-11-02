package com.cellar.wine.security.model;

import com.cellar.wine.security.password.ValidPassword;
import com.cellar.wine.utils.Regex;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotNull
    @NotEmpty(message = "Don't you have a name?!?!")
    @Size(max = 30, message = "No name is that long!")
    private String firstName;

    @NotNull
    @NotEmpty(message = "What, no surname?")
    @Size(max = 30, message = "No name is is that long!")
    private String lastName;

    @NotNull
    @NotEmpty(message = "You can't login without a Username")
    @Size(min = 6, max = 30, message = "Let's make that Username 6 characters or more")
    private String userName;

    @ValidPassword
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    @Pattern(message = "This email does not fit our requirements", regexp = Regex.EMAIL_PATTERN)
    private String email;

    public String getFirstName() {
        return firstName.substring(0, 1).toUpperCase() + (firstName.length() > 1 ? firstName.substring(1) : "");
    }

    public String getLastName() {
        return lastName.substring(0, 1).toUpperCase() + (lastName.length() > 1 ? lastName.substring(1) : "");
    }
}
