/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Region;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for region
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class RegionDto extends AbstractKeyDto {

    private Long id;

    @Size(max = 255)
    @NotEmpty
    private String name;

    @Size(max = 8192)
    private String description;

    @Size(max = 50)
    private String weblink;

    private Long countryId;

    /**
     * Default constructor
     */
    public RegionDto() {
    }

    /**
     * Constructor
     *
     * @param r The region
     */
    public RegionDto(Region r) {
        super(toKey(r.getName()));
        this.id = r.getId();
        this.name = r.getName();
        this.description = r.getDescription();
        this.weblink = r.getWeblink();
        this.countryId = r.getCountry().getId();
    }
}
