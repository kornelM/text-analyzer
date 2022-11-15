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
public class WordSearch {

    private WordSearchEnum name;
    private int numberOfSearches;
    private BigDecimal percentOfAllMultiWordSearches;
    private BigDecimal percentOfLettersPerSearch;
    private BigDecimal percentOfDigitsPerSearch;
    private BigDecimal averageNumberOfCharsPerWord;
    private BigDecimal averageNumberOfWords;

    public BigDecimal getPercentOfAllMultiWordSearches() {
        return Standarizer.standarizePercentValue(percentOfAllMultiWordSearches);
    }

    public BigDecimal getPercentOfLettersPerSearch() {
        return Standarizer.standarizePercentValue(percentOfLettersPerSearch);
    }

    public BigDecimal getPercentOfDigitsPerSearch() {
        return Standarizer.standarizePercentValue(percentOfDigitsPerSearch);
    }

    public BigDecimal getAverageNumberOfCharsPerWord() {
        return Standarizer.standarizeAvgValue(averageNumberOfCharsPerWord);
    }

    public BigDecimal getAverageNumberOfWords() {
        return Standarizer.standarizeAvgValue(averageNumberOfWords);
    }
}
