/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import info.mywinecellar.model.Barrel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * UI for barrel
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
public class BarrelUI extends AbstractKeyUI {
    private Long id;
    private String name;
    private String description;
    private String weblink;
    private String shortname;

    private Byte percentage;
    private Integer size;
    private AgingUI aging;

    /**
     * Default constructor
     */
    public BarrelUI() {
        // Empty
    }

    /**
     * Constructor
     * @param percentage The percentage
     * @param name The name
     * @param id The identifier
     * @param size The size
     * @param aging The aging
     */
    public BarrelUI(Byte percentage,
                    String name, Long id,
                    Integer size, AgingUI aging) {
        super();

        this.percentage = percentage;
        this.name = name;
        this.id = id;

        this.size = size;
        this.aging = aging;

        this.shortname = getShortName(name);
        setKey(toKey(shortname));
    }

    /**
     * Constructor
     * @param b The barrel
     */
    BarrelUI(Barrel b) {
        super();

        this.id = b.getId();
        this.name = b.getName();
        this.description = b.getDescription();
        this.weblink = b.getWeblink();

        this.shortname = getShortName(name);
        setKey(toKey(shortname));
    }

    /**
     * Set the description
     * @param s The description
     */
    public void setDescription(String s) {
        description = s;
    }

    /**
     * Set the web link
     * @param s The web link
     */
    public void setWeblink(String s) {
        weblink = s;
    }

    private String getShortName(String n) {
        if (n.indexOf("(") != -1) {
            return n.substring(0, n.indexOf("(") - 1);
        }

        return n;
    }
}

