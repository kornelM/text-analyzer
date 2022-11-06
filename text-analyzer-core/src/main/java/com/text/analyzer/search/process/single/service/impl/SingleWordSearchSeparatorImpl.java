package com.text.analyzer.search.process.single.service.impl;

import com.text.analyzer.common.service.SearchSeparator;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor
class SingleWordSearchSeparatorImpl extends SearchSeparator {

    @Override
    public Function<String, Integer> keyClassifier() {
        return String::length;
    }
}
