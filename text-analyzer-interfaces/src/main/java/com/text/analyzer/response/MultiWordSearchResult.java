package com.text.analyzer.response;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@ToString
@SuperBuilder
public class MultiWordSearchResult extends WordSearchResult {
    private List<WordSearch> wordsSearches;
}
