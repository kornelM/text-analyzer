package com.text.analyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.text.analyzer.data.FileName;
import com.text.analyzer.data.reader.SimpleFileReader;
import com.text.analyzer.data.writer.SimpleFileWriter;
import com.text.analyzer.html.pojo.HtmlCreator;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.search.TextAnalyzerService;

import java.io.IOException;

public class TextAnalyzer {

    public static void main(String[] args) throws IOException {
        SimpleFileWriter simpleFileWriter = new SimpleFileWriter();
        TextAnalyzerService textAnalyzerService = new TextAnalyzerService();
        String htmlTemplate = new SimpleFileReader().readToString(FileName.CHART_TEMPLATE.getUri());
        HtmlCreator htmlCreator = new HtmlCreator(htmlTemplate);
        AnalyzeResult analyzeResult = textAnalyzerService.analyzeTextSearches();
        String html = htmlCreator.createHtml(analyzeResult);
        simpleFileWriter.writeToFile(html, FileName.RESULT_HTML.getUri());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(analyzeResult));
    }
}
