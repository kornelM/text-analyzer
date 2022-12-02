package com.text.analyzer.response.pojo;

import com.text.analyzer.response.MultiWordSearchResult;
import com.text.analyzer.response.SingleWordSearchResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzeResult {

    private int totalNumberOfRequests;
    private Map<String, Long> mostSearchPhrases;
    private SingleWordSearchResult singleWordSearchResult;
    private MultiWordSearchResult multiWordSearchResult;
    private DigitSearchResult digitSearchResult;
}
