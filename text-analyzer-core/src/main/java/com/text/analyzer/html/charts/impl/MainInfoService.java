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
                                .with(getParagraph("name", name.name()))
                                .with(getParagraph("numberOfSearches", numberOfSearches))
                                .with(getPercentParagraph("percentOfAll", percentOfAll))
                                .with(getParagraph("averageNumberOfWords", averageNumberOfWords))
                                .with(getParagraph("theMostWordInSearch", theMostWordInSearch))
                                .with(getParagraph("theLeastWords", theLeastWords))
                                .with(getParagraph("averageNumberOfCharsPerWord", averageNumberOfCharsPerWord))
                        )
        );

        contents.addAll(
                letterSearches.stream()
                        .map(e -> TagCreator.h3(e.getName().name())
                                .with(TagCreator.h3())
                                .with(getParagraph("numberOfSearches", e.getNumberOfSearches()))
                                .with(getPercentParagraph("percentOfAllOneWordSearches", e.getPercentOfAllOneWordSearches()))
                                .with(getPercentParagraph("percentOfLetters", e.getPercentOfLetters()))
                                .with(getPercentParagraph("percentOfDigits", e.getPercentOfDigits()))
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
                                .with(getParagraph("name", name.name()))
                                .with(getParagraph("numberOfSearches", numberOfSearches))
                                .with(getPercentParagraph("percentOfAll", percentOfAll))
                                .with(getParagraph("averageNumberOfWords", averageNumberOfWords))
                                .with(getParagraph("theMostWordInSearch", theMostWordInSearch))
                                .with(getParagraph("theLeastWords", theLeastWords))
                                .with(getParagraph("averageNumberOfCharsPerWord", averageNumberOfCharsPerWord))
                        )
        );

        contents.addAll(
                wordsSearches.stream()
                        .map(e -> TagCreator.h3(e.getName().name())
                                .with(TagCreator.h3())
                                .with(getParagraph("name", e.getName().name()))
                                .with(getParagraph("numberOfSearches", e.getNumberOfSearches()))
                                .with(getPercentParagraph("percentOfAllMultiWordSearches", e.getPercentOfAllMultiWordSearches()))
                                .with(getPercentParagraph("percentOfLettersPerSearch", e.getPercentOfLettersPerSearch()))
                                .with(getPercentParagraph("percentOfDigitsPerSearch", e.getPercentOfDigitsPerSearch()))
                                .with(getParagraph("averageNumberOfCharsPerWord", e.getAverageNumberOfCharsPerWord()))
                                .with(getParagraph("averageNumberOfWords", e.getAverageNumberOfWords()))
                                .with(getParagraph("averageNumberOfChars", e.getAverageNumberOfChars()))
                                .with(getParagraph("averageNumberOfDigits", e.getAverageNumberOfDigits()))
                        )
                        .collect(Collectors.toList())
        );
        return contents;
    }

    private static PTag getParagraph(String placeholder, String value) {
        return TagCreator.p(String.format("%s: %s", placeholder, value));
    }

    private static PTag getParagraph(String placeholder, BigDecimal value) {
        return TagCreator.p(String.format("%s: %s", placeholder, value));
    }

    private static PTag getParagraph(String placeholder, int value) {
        return TagCreator.p(String.format("%s: %s", placeholder, value));
    }

    private static PTag getPercentParagraph(String placeholder, BigDecimal value) {
        return TagCreator.p(String.format("%s: %s%%", placeholder, value));
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
