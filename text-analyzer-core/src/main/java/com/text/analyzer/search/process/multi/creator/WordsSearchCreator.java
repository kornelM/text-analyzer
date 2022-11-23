package com.text.analyzer.search.process.multi.creator;

import com.text.analyzer.search.process.multi.dto.WordSearchDto;

import java.util.List;

public interface WordsSearchCreator {

    List<WordSearchDto> createWordsSearches(List<String> searches);
}
