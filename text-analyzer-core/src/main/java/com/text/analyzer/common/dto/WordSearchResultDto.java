package com.text.analyzer.common.dto;

import com.text.analyzer.response.pojo.SearchName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class WordSearchResultDto {
    private SearchName name;
    private int numberOfSearches;
    private BigDecimal averageNumberOfCharsPerWord;
    private int averageNumberOfWords;
    private int theMostWordInSearch;
    private int theLeastWords;
    private BigDecimal averageNumberOfDigits;
    //    private BigDecimal averageNumberOfCharsPerWord;
//    private BigDecimal averageNumberOfChars;
}
