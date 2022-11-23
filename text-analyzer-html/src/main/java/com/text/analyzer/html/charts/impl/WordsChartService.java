package com.text.analyzer.html.charts.impl;

import com.text.analyzer.html.charts.AbstractChartService;
import com.text.analyzer.html.charts.ChartService;
import com.text.analyzer.html.pojo.ChartName;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.response.pojo.WordSearch;
import j2html.tags.DomContent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WordsChartService extends AbstractChartService implements ChartService {

    public WordsChartService(String htmlTemplate) {
        super(htmlTemplate);
    }

    @Override
    public List<DomContent> createChart(AnalyzeResult analyzeResult) {
        List<WordSearch> wordsSearches = analyzeResult.getMultiWordSearchResult().getWordsSearches();
        wordsSearches.sort(Comparator.comparingInt(wordSearch -> wordSearch.getName().getWordLength()));

        List<String> arguments = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (WordSearch wordSearch : wordsSearches) {
            arguments.add(wordSearch.getName().name());
            values.add(wordSearch.getPercentOfAllMultiWordSearches().toString());
        }

        return getDomContents(arguments, values);
    }

    @Override
    public String getChartId() {
        return ChartName.WORDS_SEARCHES.name();
    }

    @Override
    protected String getChartTitle() {
        return ChartName.WORDS_SEARCHES.getChartTitle();
    }
}
