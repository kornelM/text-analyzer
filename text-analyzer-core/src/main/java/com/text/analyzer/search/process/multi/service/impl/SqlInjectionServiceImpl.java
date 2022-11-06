package com.text.analyzer.search.process.multi.service.impl;

import com.text.analyzer.search.process.multi.service.SqlInjectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        List<String> potentialSqlInjections = new ArrayList<>();
        for (String search : wordSearch) {
            if (isPotentialSqlInjection(search)) {
                potentialSqlInjections.add(search);
            }
        }
        return potentialSqlInjections;
    }

    private boolean isPotentialSqlInjection(String search) {
        return potentialSqlInjectionStarters.stream().anyMatch(search::contains);
    }
}
