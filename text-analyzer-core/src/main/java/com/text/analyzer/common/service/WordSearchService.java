package com.text.analyzer.common.service;

import com.text.analyzer.pojo.SearchType;

import java.util.List;

public interface WordSearchService<T> {

    T processSearches(List<String> searches);

    SearchType getType();
}
