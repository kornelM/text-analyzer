package com.text.analyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.text.analyzer.html.HtmlCreator;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.search.TextAnalyzerService;

import java.io.IOException;

public class TextAnalyzer {

    public static void main(String[] args) throws IOException {
        TextAnalyzerService textAnalyzerService = new TextAnalyzerService();
        HtmlCreator htmlCreator = new HtmlCreator();
        AnalyzeResult analyzeResult = textAnalyzerService.analyzeTextSearches();
        String html = htmlCreator.createHtml(analyzeResult);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(analyzeResult));
    }
}
