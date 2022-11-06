package com.text.analyzer.search.process.multi.service.impl;

import com.text.analyzer.common.service.SearchSeparator;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor
class MultiWordSearchSeparatorImpl extends SearchSeparator {

    public static final String EMPTY_STRING = " ";

    @Override
    public Function<String, Integer> keyClassifier() {
        return s -> s.trim().split(EMPTY_STRING).length;
    }
}
