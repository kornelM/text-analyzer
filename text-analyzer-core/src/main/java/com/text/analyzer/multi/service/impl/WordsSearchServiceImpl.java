package com.text.analyzer.multi.service.impl;

import com.text.analyzer.common.service.SearchSeparator;
import com.text.analyzer.multi.dto.WordSearchDto;
import com.text.analyzer.multi.service.MultiWordAverageService;
import com.text.analyzer.multi.service.MultiWordPercentService;
import com.text.analyzer.multi.service.WordsSearchService;
import com.text.analyzer.pojo.WordSearchEnum;

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

    public WordsSearchServiceImpl() {
        this.searchSeparator = new MultiWordSearchSeparatorImpl();
        this.multiWordPercentService = new MultiWordPercentServiceImpl();
        this.multiWordAverageService = new MultiWordAverageServiceImpl();
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

            String searchName = getSearchName(specificSearchLength);
            BigDecimal percentOfAllMultiWordSearches = multiWordPercentService.percentOfThisQueryInCompareToAll(numberOfAllMultiWordSearches, wordSearch);
            BigDecimal percentOfDigits = multiWordPercentService.getPercentOfDigits(wordSearch);
            BigDecimal percentOfLetters = multiWordPercentService.getPercentOfLetters(wordSearch);
            BigDecimal averageNumberOfCharsPerWord = multiWordAverageService.getAverageNumberOfCharsPerWord(wordSearch);
            BigDecimal averageNumberOfWordsPerSearch = multiWordAverageService.getAverageNumberOfWordsPerSearch(wordSearch);

            wordSearches.add(
                    WordSearchDto.builder()
                            .name(searchName)
                            .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                            .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch)
                            .percentOfAllMultiWordsSearches(percentOfAllMultiWordSearches)
                            .percentOfLetters(percentOfLetters)
                            .percentOfDigits(percentOfDigits)
                            .numberOfSearches(wordSearch.size())
                            .build()
            );
        }

        return process(wordSearches);
    }

    private List<WordSearchDto> process(List<WordSearchDto> wordSearches) {
        List<WordSearchDto> collect = wordSearches.stream()
                .filter(e -> WordSearchEnum.NINE_WORDS_SEARCH.getWordLength() < e.getAverageNumberOfWordsPerSearch().intValue())
                .collect(Collectors.toList());
        int numberOfSearches = 0;
        BigDecimal averageNumberOfCharsPerWord = BigDecimal.ZERO;
        BigDecimal averageNumberOfWordsPerSearch = BigDecimal.ZERO;
        BigDecimal percentOfAllMultiWordsSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        for (WordSearchDto wordSearchDto : collect) {
            numberOfSearches += wordSearchDto.getNumberOfSearches();
            averageNumberOfCharsPerWord = averageNumberOfCharsPerWord.add(wordSearchDto.getAverageNumberOfCharsPerWord());
            averageNumberOfWordsPerSearch = averageNumberOfWordsPerSearch.add(wordSearchDto.getAverageNumberOfWordsPerSearch());
            percentOfAllMultiWordsSearches = percentOfAllMultiWordsSearches.add(wordSearchDto.getPercentOfAllMultiWordsSearches());
            percentOfLetters = percentOfLetters.add(wordSearchDto.getPercentOfLetters());
            percentOfDigits = percentOfDigits.add(wordSearchDto.getPercentOfDigits());
        }

        BigDecimal numberOfDtos = BigDecimal.valueOf(collect.size());
        WordSearchDto wordSearchDto = WordSearchDto.builder()
                .name(WordSearchEnum.MORE_THAN_NINE_WORD_SEARCH.name())
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfAllMultiWordsSearches(percentOfAllMultiWordsSearches.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfLetters(percentOfLetters.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfDigits(percentOfDigits.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .numberOfSearches(numberOfSearches)
                .build();

        wordSearches.removeAll(collect);
        wordSearches.add(wordSearchDto);
        return wordSearches;
    }

    private String getSearchName(Integer specificSearchLength) {
        return WordSearchEnum.valueOf(specificSearchLength);
    }
}
