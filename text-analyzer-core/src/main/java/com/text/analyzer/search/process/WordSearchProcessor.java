package com.text.analyzer.search.process;

import java.util.List;

public interface WordSearchProcessor<T> {

    T processSearches(List<String> searches);
}
