package com.text.analyzer.search.process.multi.dto;

import com.text.analyzer.response.pojo.WordSearchEnum;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class WordSearchDto {
    private WordSearchEnum name;
    private int numberOfSearches;
    private BigDecimal averageNumberOfCharsPerWord;
    private BigDecimal averageNumberOfWordsPerSearch;
    private BigDecimal percentOfAllMultiWordSearches;
    private BigDecimal percentOfLetters;
    private BigDecimal percentOfDigits;
    private int theMostWordInSearch;
    private int theLeastWords;
    private List<String> potentialSqlInjections;
}
