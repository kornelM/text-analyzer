package com.text.analyzer.html.charts;

import com.text.analyzer.html.HtmlPlaceholder;
import com.text.analyzer.html.ScriptService;
import j2html.TagCreator;
import j2html.tags.DomContent;
import j2html.tags.specialized.CanvasTag;
import j2html.tags.specialized.ScriptTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static j2html.TagCreator.script;

public abstract class AbstractChartService {

    public static final String HTML_STRING_FORMAT_WITH_DOUBLE_QUOTES = "\"%s\"";
    public static final String COMMA_DELIMITER = ",";
    private final String scriptTemplate;

    protected AbstractChartService() {
        this.scriptTemplate = new ScriptService().getScriptTemplate();
    }

    protected abstract String getChartId();

    protected abstract String getChartTitle();

    protected List<DomContent> getDomContents(List<String> arguments, List<String> values, List<String> colors) {
        String argumentsAsString = convertListToString(arguments);
        String valuesAsString = convertListToString(values);
        String colorsAsString = convertListToString(colors);

        Map<HtmlPlaceholder, String> placeholderToValueMap = getHtmlPlaceholderStringMap(argumentsAsString, valuesAsString, colorsAsString);
        ScriptTag scriptTag = getScript(placeholderToValueMap);
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

    private ScriptTag getScript(Map<HtmlPlaceholder, String> placeholderToValueMap) {
        String script = getStringScript(placeholderToValueMap);
        return script(script);
    }

    private CanvasTag getChart() {
        String chartId = getChartId();
        return TagCreator.canvas().withId(chartId).withStyle("width:100%;max-width:1200px");
    }

    private String getStringScript(Map<HtmlPlaceholder, String> placeholderToValueMap) {
        String script = scriptTemplate;
        for (HtmlPlaceholder htmlPlaceholder : placeholderToValueMap.keySet()) {
            script = script.replace(String.format("{%s}", htmlPlaceholder.getName()), placeholderToValueMap.get(htmlPlaceholder));
        }
        return script;
    }
}
