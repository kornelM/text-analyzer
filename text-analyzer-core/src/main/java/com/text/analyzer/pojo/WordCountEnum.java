package com.text.analyzer.pojo;

public enum WordCountEnum {
    NUMBER_OF_ONE_WORD_SEARCHES(1),
    NUMBER_OF_TWO_WORDS_SEARCHES(2),
    NUMBER_OF_THREE_WORDS_SEARCHES(3),
    NUMBER_OF_FOUR_WORDS_SEARCHES(4),
    NUMBER_OF_FIVE_WORDS_SEARCHES(5),
    NUMBER_OF_SIX_WORDS_SEARCHES(6),
    NUMBER_OF_SEVEN_WORDS_SEARCHES(7),
    NUMBER_OF_EIGHT_WORDS_SEARCHES(8),
    NUMBER_OF_NINE_WORDS_SEARCHES(9),
    UNKNOWN_NUMBER_OF_WORDS(-1);

    private final int wordLength;

    WordCountEnum(int wordLength) {
        this.wordLength = wordLength;
    }

    public static String valueOf(int givenWordLength) {
        WordCountEnum[] values = WordCountEnum.values();

        for (WordCountEnum wordCountEnum : values) {
            if (wordCountEnum.wordLength == givenWordLength) {
                return wordCountEnum.name();
            }
        }
        return UNKNOWN_NUMBER_OF_WORDS.name() + "_" + givenWordLength;
    }
}
