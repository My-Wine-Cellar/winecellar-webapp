/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * UI for aging
 */
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class AgingUI implements Comparable<AgingUI>, Serializable {
    private Integer years;
    private Integer months;
    private Integer days;

    /**
     * Default constructor
     */
    public AgingUI() {
        this(0, 0, 0);
    }

    /**
     * Constructor
     * @param days The number of days
     */
    public AgingUI(Integer days) {
        int y = 0;
        int m = 0;
        int d = 0;

        while (days >= 365) {
            y += 1;
            days -= 365;
        }

        while (days >= 30) {
            m += 1;
            days -= 30;
        }

        d = days;

        this.years = y;
        this.months = m;
        this.days = d;
    }

    /**
     * Constructor
     * @param years The number of years
     * @param months The number of months
     * @param days The number of days
     */
    public AgingUI(Integer years, Integer months, Integer days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    /**
     * Get the number of days
     * @return The value
     */
    public Integer toDays() {
        return (365 * years) + (30 * months) + days;
    }

    @Override
    public int compareTo(AgingUI a) {
        return toDays().compareTo(a.toDays());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (years > 0) {
            sb = sb.append(years);
            if (years > 1) {
                sb = sb.append(" years");
            } else {
                sb = sb.append(" year");
            }
        }

        if (months > 0) {
            if (sb.length() > 0) {
                sb = sb.append(' ');
            }

            sb = sb.append(months);
            if (months > 1) {
                sb = sb.append(" months");
            } else {
                sb = sb.append(" month");
            }
        }

        if (days > 0) {
            if (sb.length() > 0) {
                sb = sb.append(' ');
            }

            sb = sb.append(days);
            if (days > 1) {
                sb = sb.append(" days");
            } else {
                sb = sb.append(" day");
            }
        }

        return sb.toString();
    }
}

