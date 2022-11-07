package com.text.analyzer.search.result.utils;

import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SearchUtils {

    public static int totalNumberOfSingleSearches(List<SingleWordSearchDto> singleWordSearchDtos) {
        return singleWordSearchDtos.stream()
                .map(SingleWordSearchDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int totalNumberOfMultiSearches(List<MultiWordSearchDto> multiWordSearchDtos) {
        return multiWordSearchDtos.stream()
                .map(MultiWordSearchDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
