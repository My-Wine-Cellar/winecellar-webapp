/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.security.model;

import info.mywinecellar.model.Bottle;
import info.mywinecellar.model.GenericTastingNotes;
import info.mywinecellar.model.Review;
import info.mywinecellar.model.Tasted;
import info.mywinecellar.model.Wishlist;
import info.mywinecellar.util.BottleSorter;
import info.mywinecellar.util.GenericTastingNotesSorter;
import info.mywinecellar.util.ReviewSorter;
import info.mywinecellar.util.TastedSorter;
import info.mywinecellar.util.WishlistSorter;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
        name = "sequence-generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "user_sequence"),
            @Parameter(name = "initial_value", value = "4"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    private int id;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "username")
    @NotEmpty(message = "*Please provide your name")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Collection<Authority> authorities;

    @OneToMany(mappedBy = "user")
    private List<Bottle> bottles;

    @OneToMany(mappedBy = "user")
    private List<GenericTastingNotes> genericTastingNotes;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<Tasted> tasted;

    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlist;

    /**
     * @return Bottle list
     */
    public List<Bottle> getBottles() {
        Collections.sort(bottles, new BottleSorter());
        return bottles;
    }

    /**
     * @return GenericTastingNotes list
     */
    public List<GenericTastingNotes> getGenericTastingNotes() {
        Collections.sort(genericTastingNotes, new GenericTastingNotesSorter());
        return genericTastingNotes;
    }

    /**
     * @return Review list
     */
    public List<Review> getReviews() {
        Collections.sort(reviews, new ReviewSorter());
        return reviews;
    }

    /**
     * @return Tasted list
     */
    public List<Tasted> getTasted() {
        Collections.sort(tasted, new TastedSorter());
        return tasted;
    }

    /**
     * @return Wishlist list
     */
    public List<Wishlist> getWishlist() {
        Collections.sort(wishlist, new WishlistSorter());
        return wishlist;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
