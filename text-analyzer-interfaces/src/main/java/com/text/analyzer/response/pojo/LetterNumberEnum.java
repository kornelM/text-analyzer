package com.text.analyzer.response.pojo;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
public enum LetterNumberEnum {

    ONE_LETTER_SEARCH(1),
    TWO_LETTER_SEARCH(2),
    THREE_LETTER_SEARCH(3),
    FOUR_LETTER_SEARCH(4),
    FIVE_LETTER_SEARCH(5),
    SIX_LETTER_SEARCH(6),
    SEVEN_LETTER_SEARCH(7),
    EIGHT_LETTER_SEARCH(8),
    NINE_LETTER_SEARCH(9),
    TEN_OR_MORE_LETTER_SEARCH(10);

    private final int wordLength;

    LetterNumberEnum(int wordLength) {
        this.wordLength = wordLength;
    }

    public static LetterNumberEnum valueOf(int givenWordLength) {
        return Arrays.stream(LetterNumberEnum.values())
                .filter(e -> !e.name().equalsIgnoreCase(TEN_OR_MORE_LETTER_SEARCH.name()))
                .filter(e -> e.wordLength == givenWordLength)
                .findFirst()
                .orElse(TEN_OR_MORE_LETTER_SEARCH);
    }
}
