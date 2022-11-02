package com.text.analyzer.response.pojo;

import com.text.analyzer.response.MultiWordSearchResult;
import com.text.analyzer.response.SingleWordSearchResult;
import lombok.Builder;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
@Builder
public class AnalyzeResult {
    private int totalNumberOfRequests;
    private Map<String, Integer> mostSearchPhrases;
    private List<SqlInjectionPhrases> potentialSqlInjections;
    private SingleWordSearchResult singleWordSearchResult;
    private MultiWordSearchResult multiWordSearchResult;
    private DigitSearchResult digitSearchResult;
}
