package com.text.analyzer.html.charts.helper;

import com.text.analyzer.html.pojo.ParagraphName;
import j2html.TagCreator;
import j2html.tags.specialized.PTag;

import java.math.BigDecimal;

public class MainInfoHelper {

    public static PTag getParagraph(ParagraphName placeholder, String value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    public static PTag getParagraph(ParagraphName placeholder, BigDecimal value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    public static PTag getParagraph(ParagraphName placeholder, int value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    public static PTag getPercentParagraph(ParagraphName placeholder, BigDecimal value) {
        return TagCreator.p(String.format("%s: %s%%", placeholder.getValue(), value));
    }
}
