package com.cellar.wine.ui;

import com.cellar.wine.security.User;

import java.util.ArrayList;
import java.util.List;

public class UserUIFactory implements FactoryUI<User, UserUI> {

    private UserUIFactory() {
    }

    public static FactoryUI<User, UserUI> instance() {
        return new UserUIFactory();
    }

    public List<UserUI> createList(List<User> users) {
        List<UserUI> result = new ArrayList<>();
        if (users != null) {
            for (User u : users) {
                result.add(create(u));
            }
        }
        return result;
    }

    public UserUI create(User u) {
        if (u == null) {
            return null;
        }

        return new UserUI(u);
    }
}
