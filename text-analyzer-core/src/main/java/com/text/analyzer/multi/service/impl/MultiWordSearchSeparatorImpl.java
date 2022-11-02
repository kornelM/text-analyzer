package com.text.analyzer.multi.service.impl;

import com.text.analyzer.common.service.SearchSeparator;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor
class MultiWordSearchSeparatorImpl extends SearchSeparator {

    @Override
    public Function<String, Integer> keyClassifier() {
        return s -> s.trim().split(" ").length;
    }
}
