package com.text.analyzer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.text.analyzer.data.configuration.PropertyLoader;
import com.text.analyzer.data.configuration.ResourceProperty;
import com.text.analyzer.data.writer.SimpleFileWriter;
import com.text.analyzer.html.pojo.HtmlCreator;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.search.TextAnalyzerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextAnalyzer {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        PropertyLoader propertyLoader = new PropertyLoader();
        log.info("Loading properties took: {} ms", System.currentTimeMillis() - start);

        String filesToAnalyzeDir = propertyLoader.getProperty(ResourceProperty.PROPERTY_TEXT_FILES_DIRECTORY);
        TextAnalyzerService textAnalyzerService = new TextAnalyzerService();

        start = System.currentTimeMillis();
        AnalyzeResult analyzeResult = textAnalyzerService.analyzeTextSearches(filesToAnalyzeDir);
        log.info("Analyzing result took: {} ms", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        HtmlCreator htmlCreator = new HtmlCreator(propertyLoader);
        log.info("Creating html took: {} ms", System.currentTimeMillis() - start);
        String html = htmlCreator.createHtml(analyzeResult);

        start = System.currentTimeMillis();
        writeResultToFile(propertyLoader, analyzeResult, html);
        log.info("Writing results to files took: {} ms", System.currentTimeMillis() - start);
    }

    private static void writeResultToFile(PropertyLoader propertyLoader, AnalyzeResult analyzeResult, String html) throws JsonProcessingException {
        SimpleFileWriter simpleFileWriter = new SimpleFileWriter();
        simpleFileWriter.writeToFile(html, propertyLoader.getProperty(ResourceProperty.PROPERTY_RESULT_HTML_FILE_DIRECTORY));

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String resultJson = objectMapper.writeValueAsString(analyzeResult);
        simpleFileWriter.writeToFile(resultJson, propertyLoader.getProperty(ResourceProperty.PROPERTY_RESULT_JSON_FILE_DIRECTORY));
    }
}
