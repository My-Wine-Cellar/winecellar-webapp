/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.dto;

import info.mywinecellar.model.Barrel;
import info.mywinecellar.model.BarrelComponent;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for barrel
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
public class BarrelDto extends AbstractKeyDto {

    private Long id;
    private String name;

    @Size(max = 8192)
    private String description;

    @Size(max = 255)
    private String weblink;

    private String shortname;

    @Max(100)
    @Digits(integer = 3, fraction = 0)
    @NotNull
    private Byte percentage;

    @NotNull
    private Integer size;

    @NotNull
    private Integer aging;

    private AgingDto agingDto;

    private Long grapeComponentId;
    private Long barrelId;

    /**
     * Default constructor
     */
    public BarrelDto() {
        // Empty
    }

    /**
     * Constructor
     *
     * @param percentage The percentage
     * @param name       The name
     * @param id         The identifier
     * @param size       The size
     * @param agingDto   The aging
     */
    public BarrelDto(Byte percentage,
                     String name, Long id,
                     Integer size, AgingDto agingDto) {
        super();

        this.percentage = percentage;
        this.name = name;
        this.id = id;

        this.size = size;
        this.agingDto = agingDto;

        this.shortname = getShortName(name);
        setKey(toKey(shortname));
    }

    /**
     * Constructor
     *
     * @param b The barrel
     */
    public BarrelDto(Barrel b) {
        super();

        this.id = b.getId();
        this.name = b.getName();
        this.description = b.getDescription();
        this.weblink = b.getWeblink();

        this.shortname = getShortName(name);
        setKey(toKey(shortname));
    }

    /**
     * Constructor
     *
     * @param bc barrelComponent
     */
    public BarrelDto(BarrelComponent bc) {
        super();
        this.percentage = bc.getPercentage();
        this.size = bc.getSize();
        this.aging = bc.getAging();
        this.grapeComponentId = bc.getGrapeComponent().getId();
        this.barrelId = bc.getBarrel().getId();
    }

    private String getShortName(String n) {
        if (n.indexOf("(") != -1) {
            return n.substring(0, n.indexOf("(") - 1);
        }
        return n;
    }
}

