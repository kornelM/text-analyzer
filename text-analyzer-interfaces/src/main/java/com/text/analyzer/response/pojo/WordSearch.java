package com.text.analyzer.response.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WordSearch extends SearchResult {
    private BigDecimal percentOfAllMultiWordSearches;
    private BigDecimal percentOfLettersPerSearch;
    private BigDecimal percentOfDigitsPerSearch;
    private BigDecimal averageNumberOfCharsPerWord;
    private BigDecimal averageNumberOfWords;
    private int averageNumberOfChars;
    private int averageNumberOfDigits;
}
