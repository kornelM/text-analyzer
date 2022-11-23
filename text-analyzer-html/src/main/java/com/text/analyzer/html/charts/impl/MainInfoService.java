package com.text.analyzer.html.charts.impl;

import com.text.analyzer.html.charts.AbstractChartService;
import com.text.analyzer.html.charts.ChartService;
import com.text.analyzer.html.pojo.ChartName;
import com.text.analyzer.html.pojo.PlaceholderName;
import com.text.analyzer.response.MultiWordSearchResult;
import com.text.analyzer.response.SingleWordSearchResult;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.response.pojo.LetterSearch;
import com.text.analyzer.response.pojo.WordSearch;
import j2html.TagCreator;
import j2html.tags.DomContent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.text.analyzer.html.charts.helper.MainInfoHelper.getParagraph;
import static com.text.analyzer.html.charts.helper.MainInfoHelper.getPercentParagraph;

public class MainInfoService extends AbstractChartService implements ChartService {

    public static final String SINGLE_NAME_MULTI_WORD = "JEDNOSŁOWNYCH";
    public static final String HEADER_NAME_MULTI_WORD = "WIELOSŁOWNYCH";
    public static final String HEADE_NAME_TITLE = "Statystyki zapytań:";

    public MainInfoService(String htmlTemplate) {
        super(htmlTemplate);
    }

    @Override
    public List<DomContent> createChart(AnalyzeResult analyzeResult) {
        List<DomContent> contents = new ArrayList<>();
        contents.add(TagCreator.div().with(TagCreator.h1(HEADE_NAME_TITLE)));

        contents.addAll(getSingleWordStatistics(analyzeResult.getSingleWordSearchResult()));
        contents.addAll(getMultiWordStatistics(analyzeResult.getMultiWordSearchResult()));
        return contents;
    }

    private List<DomContent> getSingleWordStatistics(SingleWordSearchResult analyzeResult) {
        List<DomContent> contents = new ArrayList<>();
        contents.add(
                TagCreator.div()
                        .with(TagCreator.h2(SINGLE_NAME_MULTI_WORD).withStyle(STYLE_COLOR_BLUE))
                        .with(TagCreator.div()
                                .with(TagCreator.h3())
                                .with(getParagraph(PlaceholderName.NAME_SINGLE_WORD_SEARCH, analyzeResult.getName().name()))
                                .with(getParagraph(PlaceholderName.NUMBER_OF_SEARCHES, analyzeResult.getNumberOfSearches()))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_ALL_SEARCHES, analyzeResult.getPercentOfAll()))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_WORDS, analyzeResult.getAverageNumberOfWords()))
                                .with(getParagraph(PlaceholderName.THE_MOST_WORDS_IN_SEARCH, analyzeResult.getTheMostWordInSearch()))
                                .with(getParagraph(PlaceholderName.THE_LEAST_WORDS_IN_SEARCH, analyzeResult.getTheLeastWords()))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_CHARS_PER_WORD, analyzeResult.getAverageNumberOfCharsPerWord()))
                        )
        );

        List<LetterSearch> letterSearches = analyzeResult.getLetterSearches();
        letterSearches.sort(Comparator.comparingInt(letterSearch -> letterSearch.getName().getWordLength()));
        contents.addAll(
                letterSearches.stream()
                        .map(e -> TagCreator.h3(e.getName().name())
                                .with(TagCreator.h3())
                                .with(getParagraph(PlaceholderName.NUMBER_OF_SEARCHES, e.getNumberOfSearches()))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_ALL_SINGLE_WORD_SEARCHES, e.getPercentOfAllOneWordSearches()))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_LETTERS, e.getPercentOfLetters()))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_DIGITS, e.getPercentOfDigits()))
                        )
                        .collect(Collectors.toList())
        );
        return contents;
    }

    private List<DomContent> getMultiWordStatistics(MultiWordSearchResult multiWordSearchResult) {
        List<DomContent> contents = new ArrayList<>();
        contents.add(
                TagCreator.div()
                        .with(TagCreator.h2(HEADER_NAME_MULTI_WORD).withStyle(STYLE_COLOR_BLUE))
                        .with(TagCreator.div()
                                .with(TagCreator.h3())
                                .with(getParagraph(PlaceholderName.NAME_MULTI_WORD_SEARCH, multiWordSearchResult.getName().name()))
                                .with(getParagraph(PlaceholderName.NUMBER_OF_SEARCHES, multiWordSearchResult.getNumberOfSearches()))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_ALL_SEARCHES, multiWordSearchResult.getPercentOfAll()))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_WORDS, multiWordSearchResult.getAverageNumberOfWords()))
                                .with(getParagraph(PlaceholderName.THE_MOST_WORDS_IN_SEARCH, multiWordSearchResult.getTheMostWordInSearch()))
                                .with(getParagraph(PlaceholderName.THE_LEAST_WORDS_IN_SEARCH, multiWordSearchResult.getTheLeastWords()))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_CHARS_PER_WORD, multiWordSearchResult.getAverageNumberOfCharsPerWord()))
                        )
        );

        List<WordSearch> wordsSearches = multiWordSearchResult.getWordsSearches();
        wordsSearches.sort(Comparator.comparingInt(wordSearch -> wordSearch.getName().getWordLength()));
        contents.addAll(
                wordsSearches.stream()
                        .map(e -> TagCreator.h3(e.getName().name())
                                .with(TagCreator.h3())
                                .with(getParagraph(PlaceholderName.NUMBER_OF_SEARCHES, e.getNumberOfSearches()))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_ALL_MULTI_WORD_SEARCHES, e.getPercentOfAllMultiWordSearches()))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_LETTERS, e.getPercentOfLettersPerSearch()))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_DIGITS, e.getPercentOfDigitsPerSearch()))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_CHARS_PER_WORD, e.getAverageNumberOfCharsPerWord()))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_WORDS, e.getAverageNumberOfWords()))
                        )
                        .collect(Collectors.toList())
        );
        return contents;
    }

    @Override
    public String getChartId() {
        return ChartName.MAIN_INFO.name();
    }

    @Override
    protected String getChartTitle() {
        return ChartName.MAIN_INFO.getChartTitle();
    }
}
