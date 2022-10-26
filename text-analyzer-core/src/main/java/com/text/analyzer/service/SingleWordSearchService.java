package com.text.analyzer.service;

import com.text.analyzer.dto.SingleWordSearchDto;

import java.util.List;

public interface SingleWordSearchService {

    SingleWordSearchDto processSearches(List<String> searches);
}
