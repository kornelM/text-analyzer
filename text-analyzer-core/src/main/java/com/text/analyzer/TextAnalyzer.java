package com.text.analyzer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.text.analyzer.configuration.ConfigProperty;
import com.text.analyzer.configuration.PropertyLoader;
import com.text.analyzer.data.writer.SimpleFileWriter;
import com.text.analyzer.html.pojo.HtmlCreator;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.search.TextAnalyzerService;

public class TextAnalyzer {

    public static void main(String[] args) throws Exception {
        PropertyLoader propertyLoader = new PropertyLoader();

        String filesToAnalyzeDir = propertyLoader.getProperty(ConfigProperty.PROPERTY_TEXT_FILES_DIRECTORY);
        TextAnalyzerService textAnalyzerService = new TextAnalyzerService();
        AnalyzeResult analyzeResult = textAnalyzerService.analyzeTextSearches(filesToAnalyzeDir);

        HtmlCreator htmlCreator = new HtmlCreator();
        String html = htmlCreator.createHtml(analyzeResult);

        writeResultToFile(propertyLoader, analyzeResult, html);
    }

    private static void writeResultToFile(PropertyLoader propertyLoader, AnalyzeResult analyzeResult, String html) throws JsonProcessingException {
        SimpleFileWriter simpleFileWriter = new SimpleFileWriter();
        simpleFileWriter.writeToFile(html, propertyLoader.getProperty(ConfigProperty.PROPERTY_RESULT_HTML_FILE_DIRECTORY));

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String resultJson = objectMapper.writeValueAsString(analyzeResult);
        simpleFileWriter.writeToFile(resultJson, propertyLoader.getProperty(ConfigProperty.PROPERTY_RESULT_JSON_FILE_DIRECTORY));
    }
}
