package com.text.analyzer.search.process.multi.creator.service.impl;

import com.text.analyzer.common.service.SearchSeparator;
import com.text.analyzer.common.utils.StringUtils;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor
public class MultiWordSearchSeparator extends SearchSeparator {

    @Override
    public Function<String, Integer> keyClassifier() {
        return s -> s.trim().split(StringUtils.SPACE).length;
    }
}
