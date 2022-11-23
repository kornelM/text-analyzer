package com.text.analyzer.search.process.single.dto;

import com.text.analyzer.response.pojo.SearchName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class SingleWordSearchDto {

    private SearchName name;
    private int numberOfSearches;
    private BigDecimal averageNumberOfCharsPerWord;
    private int averageNumberOfWords;
    private int theMostWordInSearch;
    private int theLeastWords;
    private BigDecimal averageNumberOfDigits;
    private List<LetterSearchDto> letterSearches;
}
