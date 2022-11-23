package com.text.analyzer.search.process.multi.creator.service.impl;

import com.text.analyzer.search.process.multi.creator.service.SqlInjectionService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SqlInjectionServiceImpl implements SqlInjectionService {

    private final Set<String> potentialSqlInjectionStarters = Set.of(
            ";drop",
            "; drop",
            "drop table",
            "drop table users",
            "drop table users --",
            "drop table * --",
            "select",
            "select *",
            "select * from",
            "select * from users",
            "select * from users *",
            "where",
            "1=1",
            "1 = 1",
            "true=true",
            "false=false"
    );

    @Override
    public List<String> findPotentialSqlInjections(List<String> wordSearch) {
        return wordSearch.stream()
                .filter(this::isPotentialSqlInjection)
                .collect(Collectors.toList());
    }

    private boolean isPotentialSqlInjection(String search) {
        return potentialSqlInjectionStarters.stream().anyMatch(search::contains);
    }
}
