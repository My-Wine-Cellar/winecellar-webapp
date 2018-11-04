package com.cellar.wine.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "producer")
public class Producer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "appellation")
    private String appellation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private Set<Wine> wines = new HashSet<>();

}
