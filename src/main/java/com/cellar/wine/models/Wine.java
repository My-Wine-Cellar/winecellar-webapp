package com.cellar.wine.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wine extends BaseEntity {

    @Builder
    public Wine(Long id, String label, Producer producer, String vintage, String varietal) {
        super(id);
        this.producer = producer;
        this.label = label;
        this.vintage = vintage;
        this.varietal = varietal;
    }

    private static final String REGEXP_STRING_PATTERN = "[a-zA-Z]+";
    private static final String REGEXP_MESSAGE = "This field cannot be empty and must be an English alphabet character";

    @Pattern(regexp = REGEXP_STRING_PATTERN, message = REGEXP_MESSAGE)
    @Column(name = "label")
    private String label;

    @ManyToOne
    @JoinColumn(name = REGEXP_STRING_PATTERN, referencedColumnName = "id")
    private Producer producer;

    @Pattern(regexp = "[0-9]+", message = "Must be valid digits 0-9")
    @Column(name = "vintage")
    private String vintage;

    @Pattern(regexp = REGEXP_STRING_PATTERN, message = REGEXP_MESSAGE)
    @Column(name = "varietal")
    private String varietal;

}
