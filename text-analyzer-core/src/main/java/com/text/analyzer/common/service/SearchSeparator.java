package com.text.analyzer.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class SearchSeparator {

    public Map<Integer, Set<String>> separateSearches(List<String> strings) {
        return strings.stream()
                .collect(Collectors.groupingBy(
                                keyClassifier(),
                                TreeMap::new,
                                Collectors.toSet()
                        )
                );
    }

    public abstract Function<String, Integer> keyClassifier();
}
