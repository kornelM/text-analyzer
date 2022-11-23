package com.text.analyzer.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileName {

    CHART_TEMPLATE("html/chart_script_template"),
    RESULT_HTML("result/result.html");

    private final String uri;
}
