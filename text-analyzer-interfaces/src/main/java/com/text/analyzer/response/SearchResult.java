package com.text.analyzer.response;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
public class SearchResult {
    private String name;
    private String numberOfSearches;
}
