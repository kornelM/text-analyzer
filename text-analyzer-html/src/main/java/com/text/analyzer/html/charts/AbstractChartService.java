package com.text.analyzer.html.charts;

import com.text.analyzer.html.charts.helper.ChartHelper;
import com.text.analyzer.html.color.Color;
import com.text.analyzer.html.pojo.HtmlPlaceholder;
import j2html.TagCreator;
import j2html.tags.DomContent;
import j2html.tags.specialized.CanvasTag;
import j2html.tags.specialized.ScriptTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractChartService {

    public static final String HTML_STRING_FORMAT_WITH_DOUBLE_QUOTES = "\"%s\"";
    public static final String COMMA_DELIMITER = ",";
    public static final String STYLE_CHART_WIDTH = "width:100%;max-width:1200px";
    public static final String STYLE_COLOR_BLUE = "color:blue";

    private final String htmlTemplate;

    protected AbstractChartService(String htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }

    protected abstract String getChartId();

    protected abstract String getChartTitle();

    protected List<DomContent> getDomContents(List<String> arguments, List<String> values) {
        List<String> colors = Color.getNumberOfColors(values.size());
        String argumentsAsString = convertListToString(arguments);
        String valuesAsString = convertListToString(values);
        String colorsAsString = convertListToString(colors);

        Map<HtmlPlaceholder, String> placeholderToValueMap = getHtmlPlaceholderStringMap(argumentsAsString, valuesAsString, colorsAsString);
        ScriptTag scriptTag = ChartHelper.getScript(htmlTemplate, placeholderToValueMap);
        CanvasTag chart = getChart();

        return List.of(chart, scriptTag);
    }

    private String convertListToString(List<String> values) {
        return values.stream()
                .map(arg -> String.format(HTML_STRING_FORMAT_WITH_DOUBLE_QUOTES, arg))
                .collect(Collectors.joining(COMMA_DELIMITER));
    }

    private Map<HtmlPlaceholder, String> getHtmlPlaceholderStringMap(String argumentsAsString, String valuesAsString, String colorsAsString) {
        Map<HtmlPlaceholder, String> placeholderToValueMap = new HashMap<>();
        placeholderToValueMap.put(HtmlPlaceholder.LIST_OF_ARGUMENTS, argumentsAsString);
        placeholderToValueMap.put(HtmlPlaceholder.LIST_OF_VALUES, valuesAsString);
        placeholderToValueMap.put(HtmlPlaceholder.LIST_OF_COLOURS, colorsAsString);
        placeholderToValueMap.put(HtmlPlaceholder.CHART_TITLE, getChartTitle());
        placeholderToValueMap.put(HtmlPlaceholder.CHART_NAME, getChartId());
        return placeholderToValueMap;
    }

    private CanvasTag getChart() {
        String chartId = getChartId();
        return TagCreator.canvas().withId(chartId).withStyle(STYLE_CHART_WIDTH);
    }
}
