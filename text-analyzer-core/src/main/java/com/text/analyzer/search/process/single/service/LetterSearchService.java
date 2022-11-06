package com.text.analyzer.search.process.single.service;

import com.text.analyzer.search.process.single.dto.LetterSearchDto;

import java.util.List;

public interface LetterSearchService {

    List<LetterSearchDto> letterSearchDtos(List<String> searches);

}
