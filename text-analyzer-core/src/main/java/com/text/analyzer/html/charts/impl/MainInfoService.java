package com.text.analyzer.html.charts.impl;

import com.text.analyzer.html.ChartName;
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
        BigDecimal averageNumberOfChars = analyzeResult.getAverageNumberOfChars();
        BigDecimal averageNumberOfCharsPerWord = analyzeResult.getAverageNumberOfCharsPerWord();
        List<LetterSearch> letterSearches = analyzeResult.getLetterSearches();
        letterSearches.sort(Comparator.comparingInt(letterSearch -> letterSearch.getName().getWordLength()));

        contents.add(
                TagCreator.div()
                        .with(TagCreator.h2("JEDNOSŁOWNYCH").withStyle("color:blue"))
                        .with(TagCreator.div()
                                .with(TagCreator.h3())
                                .with(TagCreator.p("name: " + name))
                                .with(TagCreator.p("numberOfSearches: " + numberOfSearches))
                                .with(TagCreator.p("percentOfAll: " + percentOfAll))
                                .with(TagCreator.p("averageNumberOfWords: " + averageNumberOfWords))
                                .with(TagCreator.p("theMostWordInSearch: " + theMostWordInSearch))
                                .with(TagCreator.p("theLeastWords: " + theLeastWords))
                                .with(TagCreator.p("averageNumberOfChars: " + averageNumberOfChars))
                                .with(TagCreator.p("averageNumberOfCharsPerWord: " + averageNumberOfCharsPerWord))
                        )
        );

        contents.addAll(
                letterSearches.stream()
                        .map(e -> TagCreator.h3(e.getName().name())
                                .with(TagCreator.h3())
                                .with(TagCreator.p("numberOfSearches: " + e.getNumberOfSearches()))
                                .with(TagCreator.p("percentOfAllOneWordSearches: " + e.getPercentOfAllOneWordSearches().toString()))
                                .with(TagCreator.p("percentOfLetters: " + e.getPercentOfLetters().toString()))
                                .with(TagCreator.p("percentOfDigits: " + e.getPercentOfDigits().toString()))
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
        BigDecimal averageNumberOfChars = multiWordSearchResult.getAverageNumberOfChars();
        BigDecimal averageNumberOfCharsPerWord = multiWordSearchResult.getAverageNumberOfCharsPerWord();
        List<WordSearch> wordsSearches = multiWordSearchResult.getWordsSearches();
        wordsSearches.sort(Comparator.comparingInt(wordSearch -> wordSearch.getName().getWordLength()));

        contents.add(
                TagCreator.div()
                        .with(TagCreator.h2("WIELOSŁOWNYCH").withStyle("color:blue"))
                        .with(TagCreator.div()
                                .with(TagCreator.h3())
                                .with(TagCreator.p("name: " + name))
                                .with(TagCreator.p("numberOfSearches: " + numberOfSearches))
                                .with(TagCreator.p("percentOfAll: " + percentOfAll))
                                .with(TagCreator.p("averageNumberOfWords: " + averageNumberOfWords))
                                .with(TagCreator.p("theMostWordInSearch: " + theMostWordInSearch))
                                .with(TagCreator.p("theLeastWords: " + theLeastWords))
                                .with(TagCreator.p("averageNumberOfChars: " + averageNumberOfChars))
                                .with(TagCreator.p("averageNumberOfCharsPerWord: " + averageNumberOfCharsPerWord))
                        )
        );

        contents.addAll(
                wordsSearches.stream()
                        .map(e -> TagCreator.h3(e.getName().name())
                                .with(TagCreator.h3())
                                .with(TagCreator.p("name: " + e.getName()))
                                .with(TagCreator.p("numberOfSearches: " + e.getNumberOfSearches()))
                                .with(TagCreator.p("percentOfAllMultiWordSearches: " + e.getPercentOfAllMultiWordSearches()))
                                .with(TagCreator.p("percentOfLettersPerSearch: " + e.getPercentOfLettersPerSearch()))
                                .with(TagCreator.p("percentOfDigitsPerSearch: " + e.getPercentOfDigitsPerSearch()))
                                .with(TagCreator.p("averageNumberOfCharsPerWord: " + e.getAverageNumberOfCharsPerWord()))
                                .with(TagCreator.p("averageNumberOfWords: " + e.getAverageNumberOfWords()))
                                .with(TagCreator.p("averageNumberOfChars: " + e.getAverageNumberOfChars()))
                                .with(TagCreator.p("averageNumberOfDigits: " + e.getAverageNumberOfDigits()))
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
