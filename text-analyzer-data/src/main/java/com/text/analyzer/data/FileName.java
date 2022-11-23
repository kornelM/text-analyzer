package com.text.analyzer.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileName {

    CHART_TEMPLATE("html/chart_script_template");

    private final String uri;
}
