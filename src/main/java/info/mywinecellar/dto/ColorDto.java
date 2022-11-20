/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Color;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class ColorDto extends AbstractKeyDto {

    private Long id;
    private String name;
    private String description;
    private String weblink;

    /**
     * Default constructor
     */
    public ColorDto() {
    }

    /**
     * Constructor
     *
     * @param color Color
     */
    public ColorDto(Color color) {
        this.id = color.getId();
        this.name = color.getName();
        this.description = color.getDescription();
        this.weblink = color.getWeblink();
    }

}
