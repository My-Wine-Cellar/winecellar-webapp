/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Closure;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ClosureDto extends AbstractKeyDto {

    private Long id;
    private String name;
    private String description;
    private String weblink;

    /**
     * Default constructor
     */
    public ClosureDto() {
    }

    /**
     * Constructor
     *
     * @param closure Closure
     */
    public ClosureDto(Closure closure) {
        this.id = closure.getId();
        this.name = closure.getName();
        this.description = closure.getDescription();
        this.weblink = closure.getWeblink();
    }

}
