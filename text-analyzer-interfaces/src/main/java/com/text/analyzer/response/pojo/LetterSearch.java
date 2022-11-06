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
public class LetterSearch extends SearchResult {
    private BigDecimal percentOfAllOneWordSearches;
    private BigDecimal percentOfLetters;
    private BigDecimal percentOfDigits;
}
