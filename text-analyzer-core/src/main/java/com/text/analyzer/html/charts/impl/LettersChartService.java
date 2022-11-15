package com.text.analyzer.html.charts.impl;

import com.text.analyzer.html.ChartName;
import com.text.analyzer.html.charts.AbstractChartService;
import com.text.analyzer.html.charts.ChartService;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.response.pojo.LetterSearch;
import j2html.tags.DomContent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LettersChartService extends AbstractChartService implements ChartService {

    @Override
    public List<DomContent> createChart(AnalyzeResult analyzeResult) {
        List<LetterSearch> letterSearches = analyzeResult.getSingleWordSearchResult().getLetterSearches();
        letterSearches.sort(Comparator.comparingInt(letterSearch -> letterSearch.getName().getWordLength()));

        List<String> arguments = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (LetterSearch letterSearch : letterSearches) {
            arguments.add(letterSearch.getName().name());
            values.add(letterSearch.getPercentOfAllOneWordSearches().toString());
        }

        return getDomContents(arguments, values);
    }

    @Override
    public String getChartId() {
        return ChartName.LETTERS_SEARCHES.name();
    }

    @Override
    protected String getChartTitle() {
        return ChartName.LETTERS_SEARCHES.getChartTitle();
    }
}
