package com.text.analyzer.response.pojo;

import lombok.Getter;

@Getter
public enum WordSearchEnum {
    ONE_WORD_SEARCH(1),
    TWO_WORDS_SEARCH(2),
    THREE_WORDS_SEARCH(3),
    FOUR_WORDS_SEARCH(4),
    FIVE_WORDS_SEARCH(5),
    SIX_WORDS_SEARCH(6),
    SEVEN_WORDS_SEARCH(7),
    EIGHT_WORDS_SEARCH(8),
    NINE_WORDS_SEARCH(9),
    MORE_THAN_NINE_WORD_SEARCH(10);

    private final int wordLength;

    WordSearchEnum(int wordLength) {
        this.wordLength = wordLength;
    }

    public static WordSearchEnum fromName(String wordSearchEnumString) {
        return WordSearchEnum.valueOf(wordSearchEnumString);
    }

    public static WordSearchEnum valueOf(int givenWordLength) {
        WordSearchEnum[] values = WordSearchEnum.values();

        for (WordSearchEnum wordCountEnum : values) {
            if (wordCountEnum.wordLength == givenWordLength) {
                return wordCountEnum;
            }
        }
        return MORE_THAN_NINE_WORD_SEARCH;
    }
}
