package com.text.analyzer.search.process.multi.service;

import com.text.analyzer.search.process.multi.dto.WordSearchDto;

import java.util.List;

public interface WordsSearchService {

    List<WordSearchDto> wordSearchDtos(List<String> searches);
}
