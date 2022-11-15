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
public class LetterSearch {

    private LetterNumberEnum name;
    private int numberOfSearches;
    private BigDecimal percentOfAllOneWordSearches;
    private BigDecimal percentOfLetters;
    private BigDecimal percentOfDigits;

    public BigDecimal getPercentOfAllOneWordSearches() {
        return Standarizer.standarizePercentValue(percentOfAllOneWordSearches);
    }

    public BigDecimal getPercentOfLetters() {
        return Standarizer.standarizePercentValue(percentOfLetters);
    }

    public BigDecimal getPercentOfDigits() {
        return Standarizer.standarizePercentValue(percentOfDigits);
    }
}
