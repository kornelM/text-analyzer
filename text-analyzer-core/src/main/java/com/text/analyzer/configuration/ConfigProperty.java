package com.text.analyzer.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConfigProperty {

    PROPERTY_TEXT_FILES_DIRECTORY("files.to.analyze.directory");

    private final String value;
}
