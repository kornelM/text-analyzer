package com.text.analyzer.html.charts.helper;

import com.text.analyzer.html.pojo.HtmlPlaceholder;
import j2html.tags.specialized.ScriptTag;

import java.util.Map;

import static j2html.TagCreator.script;

public class ChartHelper {

    public static ScriptTag getScript(String htmlTemplate, Map<HtmlPlaceholder, String> placeholderToValueMap) {
        String script = getStringScript(htmlTemplate, placeholderToValueMap);
        return script(script);
    }

    private static String getStringScript(String htmlTemplate, Map<HtmlPlaceholder, String> placeholderToValueMap) {
        String htmlTemplateResult = htmlTemplate;
        for (HtmlPlaceholder htmlPlaceholder : placeholderToValueMap.keySet()) {
            htmlTemplateResult = htmlTemplateResult.replace(String.format(HtmlPlaceholder.PLACEHOLDER_STRING, htmlPlaceholder.getName()), placeholderToValueMap.get(htmlPlaceholder));
        }
        return htmlTemplateResult;
    }
}
