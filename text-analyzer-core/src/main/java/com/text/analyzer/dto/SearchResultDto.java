package com.text.analyzer.dto;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
public class SearchResultDto {
    private String name;
    private String numberOfSearches;
}
