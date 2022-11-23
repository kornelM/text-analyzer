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

public class SingleWordSearchServiceImpl implements LetterSearchService {

    private final SearchSeparator searchSeparator;
    private final SingleWordPercentService singleWordDigitsService;

    public SingleWordSearchServiceImpl() {
        this.searchSeparator = new SingleWordSearchSeparatorImpl();
        this.singleWordDigitsService = new SingleWordPercentServiceImpl();
    }

    @Override
    public List<LetterSearchDto> createSingleWordSearches(List<String> searches) {
        Map<Integer, List<String>> separatedStringsByLength = searchSeparator.separateSearches(searches);
        BigDecimal numberOfAllSingleWordSearches = getNumberOfAllSingleWordSearches(separatedStringsByLength);

        List<LetterSearchDto> letterSearches = new ArrayList<>();
        for (Integer specificSearchLength : separatedStringsByLength.keySet()) {
            List<String> singleWordSearch = separatedStringsByLength.get(specificSearchLength);

            LetterNumberEnum searchName = getSearchName(specificSearchLength);
            BigDecimal percentOfAllOneWordSearches = singleWordDigitsService.percentOfThisQueryInCompareToAll(numberOfAllSingleWordSearches, singleWordSearch);
            BigDecimal percentOfDigits = singleWordDigitsService.percentOfDigits(singleWordSearch);
            BigDecimal percentOfLetters = singleWordDigitsService.percentOfLetters(singleWordSearch);

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

    private static BigDecimal getNumberOfAllSingleWordSearches(Map<Integer, List<String>> separatedStringsByLength) {
        return new BigDecimal(separatedStringsByLength.values().stream()
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum()
        );
    }

    private LetterNumberEnum getSearchName(Integer specificSearchLength) {
        return LetterNumberEnum.valueOf(specificSearchLength);
    }
}
