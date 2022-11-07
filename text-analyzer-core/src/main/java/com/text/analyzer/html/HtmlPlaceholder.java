package com.text.analyzer.html;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HtmlPlaceholder {

    LIST_OF_ARGUMENTS("listOfArguments"),
    LIST_OF_VALUES("listOfValues"),
    LIST_OF_COLOURS("listOfColours"),
    CHART_NAME("chartName"),
    CHART_TITLE("chartTitle");

    private final String name;
}
