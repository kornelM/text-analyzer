package com.text.analyzer.response.pojo;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@ToString
@SuperBuilder
public class WordSearch extends SearchResult {
    private BigDecimal percentOfAllMultiWordSearches;
    private BigDecimal percentOfLettersPerWord;
    private BigDecimal percentOfDigitsPerWord;
    private int averageNumberOfCharsPerWord;
    private int averageNumberOfChars;
    private int averageNumberOfDigits;
}
