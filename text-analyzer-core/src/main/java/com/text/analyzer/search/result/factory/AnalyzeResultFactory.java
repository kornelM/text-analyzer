package com.text.analyzer.search.result.factory;

import com.text.analyzer.response.MultiWordSearchResult;
import com.text.analyzer.response.SingleWordSearchResult;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;
import com.text.analyzer.search.result.utils.SearchUtils;

import java.util.List;

public class AnalyzeResultFactory {
    private final SingleWordResultFactory singleWordResultFactory;
    private final MultiWordResultFactory multiWordResultFactory;

    public AnalyzeResultFactory() {
        this.singleWordResultFactory = new SingleWordResultFactory();
        this.multiWordResultFactory = new MultiWordResultFactory();
    }

    public AnalyzeResult buildAnalyzeResult(List<SingleWordSearchDto> singleWordSearchDtos, List<MultiWordSearchDto> multiWordSearchDtos) {
        int totalNumberOfSingleSearches = SearchUtils.totalNumberOfSingleSearches(singleWordSearchDtos);
        int totalNumberOfMultiSearches = SearchUtils.totalNumberOfMultiSearches(multiWordSearchDtos);

        SingleWordSearchResult singleWordSearchResult = singleWordResultFactory.getSingleWordSearchResult(totalNumberOfSingleSearches, totalNumberOfMultiSearches, singleWordSearchDtos);
        MultiWordSearchResult multiWordSearchResult = multiWordResultFactory.getMultiWordSearchResult(totalNumberOfSingleSearches, totalNumberOfMultiSearches, singleWordSearchDtos, multiWordSearchDtos);

        return AnalyzeResult.builder()
                .singleWordSearchResult(singleWordSearchResult)
                .multiWordSearchResult(multiWordSearchResult)
                .totalNumberOfRequests(totalNumberOfMultiSearches + totalNumberOfSingleSearches)
                .build();
    }
}
