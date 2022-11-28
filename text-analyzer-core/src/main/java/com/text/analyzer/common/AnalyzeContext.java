package com.text.analyzer.common;

import static java.util.Objects.isNull;

public class AnalyzeContext {

    private static AnalyzeContext instance;
    private static AnalyzeDuration analyzeDuration;

    public static AnalyzeDuration getAnalyzeTimers() {
        if (isNull(instance)) {
            instance = new AnalyzeContext();
        }
        return getAnalyzeDuration();
    }

    private AnalyzeContext() {
        analyzeDuration = new AnalyzeDuration();
    }

    public static AnalyzeDuration getAnalyzeDuration() {
        return analyzeDuration;
    }
}
