package com.text.analyzer.common.service;

import java.util.List;

public interface WordSearchProcessor<T> {

    T processSearches(List<String> searches);
}
