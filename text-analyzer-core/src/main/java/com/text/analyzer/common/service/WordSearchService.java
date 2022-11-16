package com.text.analyzer.common.service;

import java.util.List;

public interface WordSearchService<T> {

    T processSearches(List<String> searches);
}
