/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    /**
     * Default constructor
     */
    public VerificationToken() {
    }

    /**
     * VerificationToken constructor
     *
     * @param token token
     * @param user  user
     */
    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationDate = calculateExpirationDate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expirationDate;

    private Date calculateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, VerificationToken.EXPIRATION);
        return new Date(calendar.getTime().getTime());
    }
}
