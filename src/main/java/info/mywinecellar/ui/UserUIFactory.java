/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.security.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * UserUI factory
 */
public class UserUIFactory implements FactoryUI<User, UserUI> {

    private UserUIFactory() {
    }

    /**
     * Create the factory
     * @return The factory
     */
    public static FactoryUI<User, UserUI> instance() {
        return new UserUIFactory();
    }

    /**
     * Create a list of UI objects
     * @param users The users
     * @return The UI objects
     */
    public List<UserUI> createList(List<User> users) {
        List<UserUI> result = new ArrayList<>();
        if (users != null) {
            for (User u : users) {
                result.add(create(u));
            }
        }
        return result;
    }

    /**
     * Create a UI object
     * @param u A user
     * @return The UI
     */
    public UserUI create(User u) {
        if (u == null) {
            return null;
        }

        return new UserUI(u);
    }
}
