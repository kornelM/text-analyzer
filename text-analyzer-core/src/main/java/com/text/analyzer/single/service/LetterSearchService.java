package com.text.analyzer.single.service;

import com.text.analyzer.single.dto.LetterSearchDto;

import java.util.List;

public interface LetterSearchService {

    List<LetterSearchDto> letterSearchDtos(List<String> searches);

}
