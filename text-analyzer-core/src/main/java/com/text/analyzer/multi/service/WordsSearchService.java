package com.text.analyzer.multi.service;

import com.text.analyzer.multi.dto.WordSearchDto;

import java.util.List;

public interface WordsSearchService {

    List<WordSearchDto> wordSearchDtos(List<String> searches);
}
