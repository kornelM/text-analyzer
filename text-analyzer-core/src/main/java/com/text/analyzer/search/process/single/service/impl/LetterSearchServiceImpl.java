package com.text.analyzer.search.process.single.service.impl;

import com.text.analyzer.common.service.SearchSeparator;
import com.text.analyzer.response.pojo.LetterNumberEnum;
import com.text.analyzer.search.process.single.dto.LetterSearchDto;
import com.text.analyzer.search.process.single.service.LetterSearchService;
import com.text.analyzer.search.process.single.service.SingleWordPercentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class LetterSearchServiceImpl implements LetterSearchService {

    private final SearchSeparator searchSeparator;
    private final SingleWordPercentService singleWordDigitsService;

    public LetterSearchServiceImpl() {
        this.searchSeparator = new SingleWordSearchSeparatorImpl();
        this.singleWordDigitsService = new SingleWordPercentServiceImpl();
    }

    private static BigDecimal getNumberOfAllSingleWordSearches(Map<Integer, List<String>> separatedStringsByLength) {
        return new BigDecimal(separatedStringsByLength.values().stream()
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum()
        );
    }

    @Override
    public List<LetterSearchDto> letterSearchDtos(List<String> searches) {
        Map<Integer, List<String>> separatedStringsByLength = searchSeparator.separateSearches(searches);
        BigDecimal numberOfAllSingleWordSearches = getNumberOfAllSingleWordSearches(separatedStringsByLength);

        List<LetterSearchDto> letterSearches = new ArrayList<>();
        for (Integer specificSearchLength : separatedStringsByLength.keySet()) {
            List<String> singleWordSearch = separatedStringsByLength.get(specificSearchLength);

            LetterNumberEnum searchName = getSearchName(specificSearchLength);
            BigDecimal percentOfAllOneWordSearches = singleWordDigitsService.percentOfThisQueryInCompareToAll(numberOfAllSingleWordSearches, singleWordSearch);
            BigDecimal percentOfDigits = singleWordDigitsService.getPercentOfDigits(singleWordSearch);
            BigDecimal percentOfLetters = singleWordDigitsService.getPercentOfLetters(singleWordSearch);

            letterSearches.add(
                    LetterSearchDto.builder()
                            .name(searchName)
                            .percentOfAllOneWordSearches(percentOfAllOneWordSearches)
                            .percentOfDigits(percentOfDigits)
                            .percentOfLetters(percentOfLetters)
                            .numberOfSearches(singleWordSearch.size())
                            .build()
            );
        }

        return letterSearches;
    }

    private LetterNumberEnum getSearchName(Integer specificSearchLength) {
        return LetterNumberEnum.valueOf(specificSearchLength);
    }
}