package com.text.analyzer.html.charts.impl;

import com.text.analyzer.html.ChartName;
import com.text.analyzer.html.PlaceholderName;
import com.text.analyzer.html.charts.AbstractChartService;
import com.text.analyzer.html.charts.ChartService;
import com.text.analyzer.response.MultiWordSearchResult;
import com.text.analyzer.response.SingleWordSearchResult;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.response.pojo.LetterSearch;
import com.text.analyzer.response.pojo.SearchName;
import com.text.analyzer.response.pojo.WordSearch;
import j2html.TagCreator;
import j2html.tags.DomContent;
import j2html.tags.specialized.PTag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainInfoService extends AbstractChartService implements ChartService {

    @Override
    public List<DomContent> createChart(AnalyzeResult analyzeResult) {
        List<DomContent> contents = new ArrayList<>();
        contents.add(TagCreator.div().with(TagCreator.h1("Statystyki zapytań:")));

        contents.addAll(getSingleWordStatistics(analyzeResult.getSingleWordSearchResult()));
        contents.addAll(getMultiWordStatistics(analyzeResult.getMultiWordSearchResult()));
        return contents;
    }

    private List<DomContent> getSingleWordStatistics(SingleWordSearchResult analyzeResult) {
        List<DomContent> contents = new ArrayList<>();
        SearchName name = analyzeResult.getName();
        int numberOfSearches = analyzeResult.getNumberOfSearches();
        BigDecimal percentOfAll = analyzeResult.getPercentOfAll();
        BigDecimal averageNumberOfWords = analyzeResult.getAverageNumberOfWords();
        int theMostWordInSearch = analyzeResult.getTheMostWordInSearch();
        int theLeastWords = analyzeResult.getTheLeastWords();
        BigDecimal averageNumberOfCharsPerWord = analyzeResult.getAverageNumberOfCharsPerWord();
        List<LetterSearch> letterSearches = analyzeResult.getLetterSearches();
        letterSearches.sort(Comparator.comparingInt(letterSearch -> letterSearch.getName().getWordLength()));

        contents.add(
                TagCreator.div()
                        .with(TagCreator.h2("JEDNOSŁOWNYCH").withStyle("color:blue"))
                        .with(TagCreator.div()
                                .with(TagCreator.h3())
                                .with(getParagraph(PlaceholderName.NAME_SINGLE_WORD_SEARCH, name.name()))
                                .with(getParagraph(PlaceholderName.NUMBER_OF_SEARCHES, numberOfSearches))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_ALL_SEARCHES, percentOfAll))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_WORDS, averageNumberOfWords))
                                .with(getParagraph(PlaceholderName.THE_MOST_WORDS_IN_SEARCH, theMostWordInSearch))
                                .with(getParagraph(PlaceholderName.THE_LEAST_WORDS_IN_SEARCH, theLeastWords))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_CHARS_PER_WORD, averageNumberOfCharsPerWord))
                        )
        );

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
        SearchName name = multiWordSearchResult.getName();
        int numberOfSearches = multiWordSearchResult.getNumberOfSearches();
        BigDecimal percentOfAll = multiWordSearchResult.getPercentOfAll();
        BigDecimal averageNumberOfWords = multiWordSearchResult.getAverageNumberOfWords();
        int theMostWordInSearch = multiWordSearchResult.getTheMostWordInSearch();
        int theLeastWords = multiWordSearchResult.getTheLeastWords();
        BigDecimal averageNumberOfCharsPerWord = multiWordSearchResult.getAverageNumberOfCharsPerWord();
        List<WordSearch> wordsSearches = multiWordSearchResult.getWordsSearches();
        wordsSearches.sort(Comparator.comparingInt(wordSearch -> wordSearch.getName().getWordLength()));

        contents.add(
                TagCreator.div()
                        .with(TagCreator.h2("WIELOSŁOWNYCH").withStyle("color:blue"))
                        .with(TagCreator.div()
                                .with(TagCreator.h3())
                                .with(getParagraph(PlaceholderName.NAME_MULTI_WORD_SEARCH, name.name()))
                                .with(getParagraph(PlaceholderName.NUMBER_OF_SEARCHES, numberOfSearches))
                                .with(getPercentParagraph(PlaceholderName.PERCENT_OF_ALL_SEARCHES, percentOfAll))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_WORDS, averageNumberOfWords))
                                .with(getParagraph(PlaceholderName.THE_MOST_WORDS_IN_SEARCH, theMostWordInSearch))
                                .with(getParagraph(PlaceholderName.THE_LEAST_WORDS_IN_SEARCH, theLeastWords))
                                .with(getParagraph(PlaceholderName.AVERAGE_NUMBER_OF_CHARS_PER_WORD, averageNumberOfCharsPerWord))
                        )
        );

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

    private static PTag getParagraph(PlaceholderName placeholder, String value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    private static PTag getParagraph(PlaceholderName placeholder, BigDecimal value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    private static PTag getParagraph(PlaceholderName placeholder, int value) {
        return TagCreator.p(String.format("%s: %s", placeholder.getValue(), value));
    }

    private static PTag getPercentParagraph(PlaceholderName placeholder, BigDecimal value) {
        return TagCreator.p(String.format("%s: %s%%", placeholder.getValue(), value));
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
