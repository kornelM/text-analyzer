package com.text.analyzer.html.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlaceholderName {

    AVERAGE_NUMBER_OF_CHARS_PER_WORD("Average number of chars per word"),
    AVERAGE_NUMBER_OF_WORDS("Average number of words"),
    NAME_MULTI_WORD_SEARCH("Multi word search"),
    NAME_SINGLE_WORD_SEARCH("Single word search"),
    NUMBER_OF_SEARCHES("Number of searches"),
    PERCENT_OF_ALL_MULTI_WORD_SEARCHES("Percent of all multi word searches"),
    PERCENT_OF_ALL_SEARCHES("Percent of all searches"),
    PERCENT_OF_ALL_SINGLE_WORD_SEARCHES("Percent of all single word searches"),
    PERCENT_OF_DIGITS("Percent of digits in searches"),
    PERCENT_OF_LETTERS("Percent of letters in searches"),
    THE_LEAST_WORDS_IN_SEARCH("The least words in search"),
    THE_MOST_WORDS_IN_SEARCH("The most words in search");

    private final String value;
}
