package com.text.analyzer.single.service.impl;

import com.text.analyzer.common.service.SearchSeparator;
import com.text.analyzer.pojo.LetterNumberEnum;
import com.text.analyzer.single.dto.LetterSearchDto;
import com.text.analyzer.single.service.LetterSearchService;
import com.text.analyzer.single.service.SingleWordPercentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class LetterSearchServiceImpl implements LetterSearchService {

    private final SearchSeparator searchSeparator;
    private final SingleWordPercentService singleWordDigitsService;

    public LetterSearchServiceImpl() {
        this.searchSeparator = new SingleWordSearchSeparatorImpl();
        this.singleWordDigitsService = new SingleWordPercentServiceImpl();
    }

    private static BigDecimal getNumberOfAllSingleWordSearches(Map<Integer, Set<String>> separatedStringsByLength) {
        return new BigDecimal(separatedStringsByLength.values().stream()
                .map(Set::size)
                .mapToInt(Integer::intValue)
                .sum()
        );
    }

    @Override
    public List<LetterSearchDto> letterSearchDtos(List<String> searches) {
        Map<Integer, Set<String>> separatedStringsByLength = searchSeparator.separateSearches(searches);
        BigDecimal numberOfAllSingleWordSearches = getNumberOfAllSingleWordSearches(separatedStringsByLength);

        List<LetterSearchDto> letterSearches = new ArrayList<>();
        for (Integer specificSearchLength : separatedStringsByLength.keySet()) {
            Set<String> singleWordSearch = separatedStringsByLength.get(specificSearchLength);

            String searchName = getSearchName(specificSearchLength);
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

    private String getSearchName(Integer specificSearchLength) {
        return LetterNumberEnum.valueOf(specificSearchLength);
    }
}
