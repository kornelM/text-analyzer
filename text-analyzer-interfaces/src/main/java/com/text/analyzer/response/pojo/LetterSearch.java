package com.text.analyzer.response.pojo;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@ToString
@SuperBuilder
public class LetterSearch extends SearchResult {
    private BigDecimal percentOfAllOneWordSearches;
    private BigDecimal percentOfLetters;
    private BigDecimal percentOfDigits;
}
