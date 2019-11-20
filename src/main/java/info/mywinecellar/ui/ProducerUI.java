/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Producer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * UI for wishlist
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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

    /**
     * Constructor
     * @param p The producer
     */
    ProducerUI(Producer p) {
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
