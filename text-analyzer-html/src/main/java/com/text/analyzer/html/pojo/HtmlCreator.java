package com.text.analyzer.html.pojo;

import com.text.analyzer.html.charts.ChartService;
import com.text.analyzer.html.charts.impl.LettersChartService;
import com.text.analyzer.html.charts.impl.MainChartService;
import com.text.analyzer.html.charts.impl.MainInfoService;
import com.text.analyzer.html.charts.impl.WordsChartService;
import com.text.analyzer.response.pojo.AnalyzeResult;
import j2html.tags.DomContent;

import java.util.Collection;
import java.util.List;

import static j2html.TagCreator.body;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.link;
import static j2html.TagCreator.meta;
import static j2html.TagCreator.script;
import static j2html.TagCreator.title;

public class HtmlCreator {

    private final List<ChartService> chartServices;

    public HtmlCreator(String htmlTemplate) {
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
                        meta().withContent("content-type").withCharset("utf-8"),
                        title("Title"),
                        link().withRel("stylesheet").withHref("/css/main.css"),
                        script().withSrc("https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js")
                ),
                body(domContents)
        )
                .renderFormatted();
        System.out.println(renderedHtml);
        return renderedHtml;
    }
}
