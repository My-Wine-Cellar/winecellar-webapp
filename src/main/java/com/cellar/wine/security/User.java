package com.cellar.wine.security;

import com.cellar.wine.models.Bottle;
import com.cellar.wine.models.GenericTastingNotes;
import com.cellar.wine.models.Review;
import com.cellar.wine.models.Tasted;
import com.cellar.wine.models.Wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
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
