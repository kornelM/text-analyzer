package com.text.analyzer.dto;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@ToString
@SuperBuilder
public class WordSearchResultDto extends SearchResultDto {
    private BigDecimal percentOfAll;
    private BigDecimal averageNumberOfWords;
    private int theMostWordInSearch;
    private int theLeastWords;
    private BigDecimal averageNumberOfChars;
    private BigDecimal averageNumberOfDigits;
    private BigDecimal averageNumberOfCharsPerWord;
}
