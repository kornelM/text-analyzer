package com.text.analyzer.single.service;

import com.text.analyzer.common.dto.SearchResultDto;
import com.text.analyzer.pojo.SearchType;

import java.util.List;

public interface WordSearchService<T extends SearchResultDto> {

    T processSearches(List<String> searches);

    SearchType getType();
}
