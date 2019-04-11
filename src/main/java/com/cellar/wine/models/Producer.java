package com.cellar.wine.models;

import com.cellar.wine.security.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.cellar.wine.utils.Regex.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Producer extends BaseEntity {

    @Builder
    public Producer(Long id, String name, String country, String appellation, Set<Wine> wines) {
        super(id);
        this.name = name;
        this.country = country;
        this.appellation = appellation;
        if(wines != null) {
            this.wines = wines;
        }
    }

    @Size(min = 1, max = 20)
    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_MESSAGE)
    @Column(name = "name")
    private String name;

    @Size(min = 1, max = 20)
    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_MESSAGE)
    @Column(name = "country")
    private String country;

    @Size(min = 1, max = 20)
    @Pattern(regexp = STRING_NO_SPACES_PATTERN, message = STRING_NO_SPACES_MESSAGE)
    @Column(name = "appellation")
    private String appellation;

    @Size(min = 1, max = 20)
    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_MESSAGE)
    @Column(name = "province")
    private String province;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private Set<Wine> wines = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Wine getWine(String label) {
        return getWine(label, false);
    }

    public Wine getWine(String label, boolean ignoreNew) {
        label = label.toLowerCase();
        for (Wine wine : wines) {
            if (!ignoreNew || !wine.isNew()) {
                String compName = wine.getLabel();
                compName = compName.toLowerCase();
                if(compName.equals(label)) {
                    return wine;
                }
            }
        }
        return null;
    }
}