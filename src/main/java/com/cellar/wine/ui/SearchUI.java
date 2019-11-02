package com.cellar.wine.ui;

import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class SearchUI {

    private SearchResult type;
    private Long id;
    private String name;
    private String description;

    public SearchUI(SearchResult type, Long id, String name, String description) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public enum SearchResult {
        AREA,
        COUNTRY,
        PRODUCER,
        REGION,
        WINE
    }
}
