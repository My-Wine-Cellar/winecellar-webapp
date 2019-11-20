/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Search result
 */
@EqualsAndHashCode
@Getter
@ToString
public class SearchUI implements Comparable<SearchUI> {
    private SearchResult type;
    private Long id;
    private String name;
    private String description;
    private Double rank;

    /**
     * SearchUI
     * @param type The type
     * @param id The primary key
     * @param name The name
     * @param description The description
     * @param rank The search rank
     */
    public SearchUI(SearchResult type, Long id, String name, String description, Double rank) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.description = description;
        this.rank = rank;
    }

    /**
     * Compare against another search object
     * @param su The object
     * @return 0 if equals, 1 if this is smaller than su, -1 if this is greater than su
     */
    @Override
    public int compareTo(SearchUI su) {
        int c = rank.compareTo(su.getRank());

        if (c != 0) {
            return -c;
        }

        return name.compareTo(su.getName());
    }

    /**
     * The search result type
     */
    public enum SearchResult {
        /** AREA */
        AREA,
        /** COUNTRY */
        COUNTRY,
        /** PRODUCER */
        PRODUCER,
        /** REGION */
        REGION,
        /** WINE */
        WINE
    }
}
