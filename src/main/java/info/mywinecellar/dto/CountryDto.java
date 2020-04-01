/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Country;

import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for country
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class CountryDto extends AbstractKeyDto {

    private Long id;
    private String name;
    private String flag;

    @Size(max = 255)
    private String description;

    @Size(max = 50)
    private String weblink;

    /**
     * Default constructor
     */
    public CountryDto() {
    }

    /**
     * Constructor
     *
     * @param c The country
     */
    public CountryDto(Country c) {
        super(toKey(c.getName()));
        this.id = c.getId();
        this.name = c.getName();
        this.flag = c.getFlag();
        this.description = c.getDescription();
        this.weblink = c.getWeblink();
    }
}
