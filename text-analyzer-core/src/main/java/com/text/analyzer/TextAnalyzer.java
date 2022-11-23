package com.text.analyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.text.analyzer.configuration.ConfigProperty;
import com.text.analyzer.configuration.PropertyLoader;
import com.text.analyzer.data.FileName;
import com.text.analyzer.data.reader.SimpleFileReader;
import com.text.analyzer.data.writer.SimpleFileWriter;
import com.text.analyzer.html.pojo.HtmlCreator;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.search.TextAnalyzerService;

import java.io.IOException;

public class TextAnalyzer {


    public static void main(String[] args) throws IOException {
        PropertyLoader propertyLoader = new PropertyLoader();

        String filesToAnalyzeDir = propertyLoader.getProperty(ConfigProperty.PROPERTY_TEXT_FILES_DIRECTORY);
        TextAnalyzerService textAnalyzerService = new TextAnalyzerService();
        AnalyzeResult analyzeResult = textAnalyzerService.analyzeTextSearches(filesToAnalyzeDir);

        String htmlTemplate = new SimpleFileReader().readToString(FileName.CHART_TEMPLATE.getUri());
        HtmlCreator htmlCreator = new HtmlCreator(htmlTemplate);
        String html = htmlCreator.createHtml(analyzeResult);

        String resultFileDir = propertyLoader.getProperty(ConfigProperty.PROPERTY_RESULT_FILE_DIRECTORY);
        SimpleFileWriter simpleFileWriter = new SimpleFileWriter();
        simpleFileWriter.writeToFile(html, resultFileDir);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(analyzeResult));
    }
}
