package com.text.analyzer.search.process.multi.creator.service;

import java.util.List;

public interface SqlInjectionService {

    List<String> findPotentialSqlInjections(List<String> wordSearch);
}
