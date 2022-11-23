package com.text.analyzer.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConfigProperty {

    PROPERTY_TEXT_FILES_DIRECTORY("files.to.analyze.directory"),
    PROPERTY_RESULT_FILE_DIRECTORY("result.directory.file.dir");

    private final String value;
}
