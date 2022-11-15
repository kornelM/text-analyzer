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
public class DigitSearchResult {

    private BigDecimal percentOfAll;
    private int averageNumberOfWords;
    private int theMostWords;
    private int theLeastWords;
    private int averageNumberOfCharsPerWord;
    private int averageNumberOfChars;
}
