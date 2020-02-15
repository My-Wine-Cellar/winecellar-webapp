/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.service;

import info.mywinecellar.security.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @param username username
     * @return User
     */
    User findUserByUsername(String username);

    /**
     * @param email email
     * @return User
     */
    User findUserByEmail(String email);

}
