package com.text.analyzer.response.pojo;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@ToString
@SuperBuilder
public class DigitSearchResult extends SearchResult {
    private BigDecimal percentOfAll;
    private int averageNumberOfWords;
    private int theMostWords;
    private int theLeastWords;
    private int averageNumberOfCharsPerWord;
    private int averageNumberOfChars;
}
