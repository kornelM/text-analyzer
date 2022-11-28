package com.text.analyzer.common.service;

import com.text.analyzer.common.utils.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class SearchSeparator {

    public Map<Integer, List<String>> separateSearches(List<String> strings) {
        return strings.stream()
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.groupingBy(
                                keyClassifier(),
                                Collectors.toList()
                        )
                );
    }

    public abstract Function<String, Integer> keyClassifier();
}
