package com.text.analyzer.data.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceProperty {

    PROPERTY_TEXT_FILES_DIRECTORY("files.to.analyze.directory"),
    PROPERTY_RESULT_HTML_FILE_DIRECTORY("result.html.file.dir"),
    PROPERTY_RESULT_JSON_FILE_DIRECTORY("result.json.file.dir"),
    PROPERTY_HTML_CHART_TEMPLATE("resource.html.chart.template.path"),
    PROPERTY_MOST_SEARCHED_PHRASES_LIMIT("most.searched.phrases.limit");

    private final String value;
}
