package com.text.analyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.search.TextAnalyzerService;

import java.io.IOException;

public class TextAnalyzer {

    public static void main(String[] args) throws IOException {
        TextAnalyzerService textAnalyzerService = new TextAnalyzerService();
        AnalyzeResult analyzeResult = textAnalyzerService.analyzeTextSearches();

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(analyzeResult));
    }
}
