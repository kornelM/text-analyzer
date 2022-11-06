package com.text.analyzer.response.pojo;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
public class SearchResult {
    private String name;
    private int numberOfSearches;
}
