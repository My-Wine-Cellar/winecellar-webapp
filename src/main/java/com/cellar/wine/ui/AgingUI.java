package com.cellar.wine.ui;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * Aging bean
 */
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class AgingUI implements Comparable<AgingUI>, Serializable {
    private Integer years;
    private Integer months;
    private Integer days;
    
    public AgingUI() {
        this(0, 0, 0);
    }

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

    public AgingUI(Integer years, Integer months, Integer days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

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
            if (sb.length() > 0)
                sb = sb.append(' ');
                
            sb = sb.append(months);
            if (months > 1) {
                sb = sb.append(" months");
            } else {
                sb = sb.append(" month");
            }
        }

        if (days > 0) {
            if (sb.length() > 0)
                sb = sb.append(' ');
                
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

