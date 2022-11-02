package com.text.analyzer.multi.dto;

import com.text.analyzer.common.dto.SearchResultDto;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class WordSearchResultDto extends SearchResultDto {
    private BigDecimal averageNumberOfWords;
    private int theMostWordInSearch;
    private int theLeastWords;
    private BigDecimal averageNumberOfCharsPerWord;
    private BigDecimal averageNumberOfChars;
    private int averageNumberOfDigits;
}
