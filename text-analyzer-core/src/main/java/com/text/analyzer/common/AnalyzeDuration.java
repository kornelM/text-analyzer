package com.text.analyzer.common;

public class AnalyzeDuration {

    private final long startTime;
    private long singleWordProcessingTime;

    public AnalyzeDuration() {
        long now = System.currentTimeMillis();
        this.startTime = now;
        this.singleWordProcessingTime = now;
    }

    public String getSingleWordProcessingTime() {
        return "Single word searches processing time: " + (singleWordProcessingTime - startTime);
    }

    public void startSingleSearchProcessing() {
        singleWordProcessingTime = System.currentTimeMillis();
    }
}
