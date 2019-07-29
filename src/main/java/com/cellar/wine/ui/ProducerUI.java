package com.cellar.wine.ui;

import com.cellar.wine.models.Producer;

import lombok.*;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
@Setter
public class ProducerUI extends AbstractKeyUI {

    private Long id;
    private String name;
    private String description;
    private String phone;
    private String fax;
    private String email;
    private String website;

    public ProducerUI(Producer p) {
        super(toKey(p.getName()));
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.phone = p.getPhone();
        this.fax = p.getFax();
        this.email = p.getEmail();
        this.website = p.getWebsite();
    }
}
