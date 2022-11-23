package com.text.analyzer.html.charts.helper;

import com.text.analyzer.html.pojo.PlaceholderName;
import j2html.TagCreator;
import j2html.tags.specialized.PTag;

import java.math.BigDecimal;

public class MainInfoHelper {

    public static PTag getParagraph(PlaceholderName placeholder, String value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    public static PTag getParagraph(PlaceholderName placeholder, BigDecimal value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    public static PTag getParagraph(PlaceholderName placeholder, int value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    public static PTag getPercentParagraph(PlaceholderName placeholder, BigDecimal value) {
        return TagCreator.p(String.format("%s: %s%%", placeholder.getValue(), value));
    }
}
