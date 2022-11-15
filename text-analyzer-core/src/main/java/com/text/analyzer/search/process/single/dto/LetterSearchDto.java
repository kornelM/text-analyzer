package com.text.analyzer.search.process.single.dto;

import com.text.analyzer.response.pojo.LetterNumberEnum;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class LetterSearchDto {

    private LetterNumberEnum name;
    private int numberOfSearches;
    private BigDecimal percentOfAllOneWordSearches;
    private BigDecimal percentOfLetters;
    private BigDecimal percentOfDigits;
}
