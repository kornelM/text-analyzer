package com.text.analyzer.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConfigProperty {

    PROPERTY_TEXT_FILES_DIRECTORY("files.to.analyze.directory"),
    PROPERTY_RESULT_HTML_FILE_DIRECTORY("result.html.directory.file.dir"),
    PROPERTY_RESULT_JSON_FILE_DIRECTORY("result.json.directory.file.dir");

    private final String value;
}
