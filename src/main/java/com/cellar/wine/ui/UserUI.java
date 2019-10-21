package com.cellar.wine.ui;

import com.cellar.wine.security.model.User;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class UserUI implements Serializable {

    private int id;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;

    UserUI(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.firstName = u.getFirstName();
        this.middleName = u.getMiddleName();
        this.lastName = u.getLastName();
    }
}
