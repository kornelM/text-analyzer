package com.text.analyzer.html.pojo;

import com.text.analyzer.data.FileName;
import com.text.analyzer.data.reader.AnalyzerFileReader;
import com.text.analyzer.data.reader.SimpleFileReader;
import com.text.analyzer.html.charts.ChartService;
import com.text.analyzer.html.charts.impl.LettersChartService;
import com.text.analyzer.html.charts.impl.MainChartService;
import com.text.analyzer.html.charts.impl.MainInfoService;
import com.text.analyzer.html.charts.impl.WordsChartService;
import com.text.analyzer.response.pojo.AnalyzeResult;
import j2html.tags.DomContent;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

import static j2html.TagCreator.body;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.meta;
import static j2html.TagCreator.script;

public class HtmlCreator {

    public static final String IMPORT_CHART_JS_ADDRESS = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js";
    public static final String HTML_CONTENT_TYPE_NAME = "content-type";

    private final List<ChartService> chartServices;

    public HtmlCreator() {
        AnalyzerFileReader analyzerFileReader = new SimpleFileReader();
        String htmlTemplate = analyzerFileReader.readToString(FileName.CHART_TEMPLATE.getUri());
        this.chartServices = List.of(
                new MainInfoService(htmlTemplate),
                new MainChartService(htmlTemplate),
                new LettersChartService(htmlTemplate),
                new WordsChartService(htmlTemplate)
        );
    }

    public String createHtml(AnalyzeResult analyzeResult) {
        DomContent[] domContents = chartServices.stream()
                .map(s -> s.createChart(analyzeResult))
                .flatMap(Collection::stream).toArray(DomContent[]::new);

        String renderedHtml = html(
                head(
                        meta().withContent(HTML_CONTENT_TYPE_NAME).withCharset(StandardCharsets.UTF_8.name()),
                        script().withSrc(IMPORT_CHART_JS_ADDRESS)
                ),
                body(domContents)
        )
                .renderFormatted();
        return renderedHtml;
    }
}
