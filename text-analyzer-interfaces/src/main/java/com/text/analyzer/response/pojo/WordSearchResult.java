package com.text.analyzer.response.pojo;

import com.text.analyzer.response.utils.Standarizer;
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
public class WordSearchResult {

    private SearchName name;
    private int numberOfSearches;
    private BigDecimal percentOfAll;
    private BigDecimal averageNumberOfWords;
    private int theMostWordInSearch;
    private int theLeastWords;
    private BigDecimal averageNumberOfDigits;
    private BigDecimal averageNumberOfCharsPerWord;

    public BigDecimal getPercentOfAll() {
        return Standarizer.standarizePercentValue(percentOfAll);
    }

    public BigDecimal getAverageNumberOfWords() {
        return Standarizer.standarizeAvgValue(averageNumberOfWords);
    }

    public BigDecimal getAverageNumberOfDigits() {
        return Standarizer.standarizeAvgValue(averageNumberOfDigits);
    }

    public BigDecimal getAverageNumberOfCharsPerWord() {
        return Standarizer.standarizeAvgValue(averageNumberOfCharsPerWord);
    }
}
