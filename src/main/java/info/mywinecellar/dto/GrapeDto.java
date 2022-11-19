/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Grape;

import jakarta.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for grape
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class GrapeDto extends AbstractKeyDto {

    private Long id;
    private String name;
    private String color;
    private String alternativeNames;

    @Size(max = 8192)
    private String description;

    @Size(max = 255)
    private String weblink;

    /**
     * Default constructor
     */
    public GrapeDto() {
    }

    /**
     * Constructor
     *
     * @param g The grape
     */
    public GrapeDto(Grape g) {
        super(toKey(g.getName()));

        this.id = g.getId();
        this.name = g.getName();
        this.color = g.getColor();
        this.description = g.getDescription();
        this.weblink = g.getWeblink();
    }
}
