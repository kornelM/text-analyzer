package com.text.analyzer.search.process.multi.service.impl;

import com.text.analyzer.common.service.SearchSeparator;
import com.text.analyzer.response.pojo.WordSearchEnum;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import com.text.analyzer.search.process.multi.service.MultiWordAverageService;
import com.text.analyzer.search.process.multi.service.MultiWordPercentService;
import com.text.analyzer.search.process.multi.service.WordsSearchService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordsSearchServiceImpl implements WordsSearchService {

    private final SearchSeparator searchSeparator;
    private final MultiWordPercentService multiWordPercentService;
    private final MultiWordAverageService multiWordAverageService;
    private final SqlInjectionServiceImpl sqlInjectionService;

    public WordsSearchServiceImpl() {
        this.searchSeparator = new MultiWordSearchSeparatorImpl();
        this.multiWordPercentService = new MultiWordPercentServiceImpl();
        this.multiWordAverageService = new MultiWordAverageServiceImpl();
        this.sqlInjectionService = new SqlInjectionServiceImpl();
    }

    private static BigDecimal getNumberOfAllSearches(Map<Integer, List<String>> separatedStringsByLength) {
        return new BigDecimal(separatedStringsByLength.values().stream()
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum()
        );
    }

    @Override
    public List<WordSearchDto> wordSearchDtos(List<String> searches) {
        Map<Integer, List<String>> separatedStringsByNumberOfWords = searchSeparator.separateSearches(searches);
        BigDecimal numberOfAllMultiWordSearches = getNumberOfAllSearches(separatedStringsByNumberOfWords);

        List<WordSearchDto> wordSearches = new ArrayList<>();
        for (Integer specificSearchLength : separatedStringsByNumberOfWords.keySet()) {
            List<String> wordSearch = separatedStringsByNumberOfWords.get(specificSearchLength);

            WordSearchEnum searchName = getSearchName(specificSearchLength);
            BigDecimal averageNumberOfCharsPerWord = multiWordAverageService.getAverageNumberOfCharsPerWord(wordSearch);
            BigDecimal averageNumberOfWordsPerSearch = multiWordAverageService.getAverageNumberOfWordsPerSearch(wordSearch);
            BigDecimal percentOfAllMultiWordSearches = multiWordPercentService.percentOfThisQueryInCompareToAll(numberOfAllMultiWordSearches, wordSearch);
            BigDecimal percentOfLetters = multiWordPercentService.getPercentOfLetters(wordSearch);
            BigDecimal percentOfDigits = multiWordPercentService.getPercentOfDigits(wordSearch);
            int numberOfSearches = wordSearch.size();
            List<String> potentialSqlInjections = sqlInjectionService.findPotentialSqlInjections(wordSearch);

            wordSearches.add(
                    WordSearchDto.builder()
                            .name(searchName)
                            .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                            .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch)
                            .percentOfAllMultiWordsSearches(percentOfAllMultiWordSearches)
                            .percentOfLetters(percentOfLetters)
                            .percentOfDigits(percentOfDigits)
                            .numberOfSearches(numberOfSearches)
                            .potentialSqlInjections(potentialSqlInjections)
                            .build()
            );
        }

        return mergeWordSearches(wordSearches);
    }

    private List<WordSearchDto> mergeWordSearches(List<WordSearchDto> wordSearches) {
        List<WordSearchDto> collect = wordSearches.stream()
                .filter(e -> WordSearchEnum.NINE_WORDS_SEARCH.getWordLength() < e.getAverageNumberOfWordsPerSearch().intValue())
                .collect(Collectors.toList());
        int numberOfSearches = 0;
        BigDecimal averageNumberOfCharsPerWord = BigDecimal.ZERO;
        BigDecimal averageNumberOfWordsPerSearch = BigDecimal.ZERO;
        BigDecimal percentOfAllMultiWordsSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        boolean isCreated = false;
        for (WordSearchDto wordSearchDto : collect) {
            isCreated = true;
            numberOfSearches += wordSearchDto.getNumberOfSearches();
            averageNumberOfCharsPerWord = averageNumberOfCharsPerWord.add(wordSearchDto.getAverageNumberOfCharsPerWord());
            averageNumberOfWordsPerSearch = averageNumberOfWordsPerSearch.add(wordSearchDto.getAverageNumberOfWordsPerSearch());
            percentOfAllMultiWordsSearches = percentOfAllMultiWordsSearches.add(wordSearchDto.getPercentOfAllMultiWordsSearches());
            percentOfLetters = percentOfLetters.add(wordSearchDto.getPercentOfLetters());
            percentOfDigits = percentOfDigits.add(wordSearchDto.getPercentOfDigits());
        }

        if (isCreated) {
            BigDecimal numberOfDtos = BigDecimal.valueOf(collect.size());
            WordSearchDto wordSearchDto = WordSearchDto.builder()
                    .name(WordSearchEnum.MORE_THAN_NINE_WORD_SEARCH)
                    .averageNumberOfCharsPerWord(!numberOfDtos.equals(BigDecimal.ZERO) ? averageNumberOfCharsPerWord.divide(numberOfDtos, RoundingMode.HALF_EVEN) : BigDecimal.ZERO)
                    .averageNumberOfWordsPerSearch(!numberOfDtos.equals(BigDecimal.ZERO) ? averageNumberOfWordsPerSearch.divide(numberOfDtos, RoundingMode.HALF_EVEN) : BigDecimal.ZERO)
                    .percentOfAllMultiWordsSearches(!numberOfDtos.equals(BigDecimal.ZERO) ? percentOfAllMultiWordsSearches.divide(numberOfDtos, RoundingMode.HALF_EVEN) : BigDecimal.ZERO)
                    .percentOfLetters(!numberOfDtos.equals(BigDecimal.ZERO) ? percentOfLetters.divide(numberOfDtos, RoundingMode.HALF_EVEN) : BigDecimal.ZERO)
                    .percentOfDigits(!numberOfDtos.equals(BigDecimal.ZERO) ? percentOfDigits.divide(numberOfDtos, RoundingMode.HALF_EVEN) : BigDecimal.ZERO)
                    .numberOfSearches(numberOfSearches)
                    .build();
            wordSearches.removeAll(collect);
            wordSearches.add(wordSearchDto);
        }

        return wordSearches;
    }

    private WordSearchEnum getSearchName(Integer specificSearchLength) {
        return WordSearchEnum.valueOf(specificSearchLength);
    }
}