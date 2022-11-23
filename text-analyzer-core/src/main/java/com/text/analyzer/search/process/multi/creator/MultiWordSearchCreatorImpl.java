package com.text.analyzer.search.process.multi.creator;

import com.text.analyzer.common.service.SearchSeparator;
import com.text.analyzer.common.utils.NumberUtils;
import com.text.analyzer.response.pojo.WordSearchEnum;
import com.text.analyzer.search.process.multi.creator.service.MultiWordAverageService;
import com.text.analyzer.search.process.multi.creator.service.MultiWordPercentService;
import com.text.analyzer.search.process.multi.creator.service.impl.MultiWordAverageServiceImpl;
import com.text.analyzer.search.process.multi.creator.service.impl.MultiWordPercentServiceImpl;
import com.text.analyzer.search.process.multi.creator.service.impl.MultiWordSearchSeparator;
import com.text.analyzer.search.process.multi.creator.service.impl.SqlInjectionServiceImpl;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MultiWordSearchCreatorImpl implements WordsSearchCreator {

    private final SearchSeparator searchSeparator;
    private final MultiWordPercentService multiWordPercentService;
    private final MultiWordAverageService multiWordAverageService;
    private final SqlInjectionServiceImpl sqlInjectionService;

    public MultiWordSearchCreatorImpl() {
        this.searchSeparator = new MultiWordSearchSeparator();
        this.multiWordPercentService = new MultiWordPercentServiceImpl();
        this.multiWordAverageService = new MultiWordAverageServiceImpl();
        this.sqlInjectionService = new SqlInjectionServiceImpl();
    }

    @Override
    public List<WordSearchDto> createWordsSearches(List<String> searches) {
        Map<Integer, List<String>> separatedStringsByNumberOfWords = searchSeparator.separateSearches(searches);
        int numberOfAllMultiWordSearches = getNumberOfAllSearches(separatedStringsByNumberOfWords);

        List<WordSearchDto> wordSearches = new ArrayList<>();
        for (Integer specificSearchLength : separatedStringsByNumberOfWords.keySet()) {
            List<String> wordSearch = separatedStringsByNumberOfWords.get(specificSearchLength);

            WordSearchEnum searchName = getSearchName(specificSearchLength);
            BigDecimal averageNumberOfCharsPerWord = multiWordAverageService.averageNumberOfCharsPerWord(wordSearch);
            BigDecimal averageNumberOfWordsPerSearch = multiWordAverageService.averageNumberOfWordsPerSearch(wordSearch);
            BigDecimal percentOfAllMultiWordSearches = multiWordPercentService.percentOfThisQueryInCompareToAll(numberOfAllMultiWordSearches, wordSearch);
            BigDecimal percentOfLetters = multiWordPercentService.percentOfLetters(wordSearch);
            BigDecimal percentOfDigits = multiWordPercentService.percentOfDigits(wordSearch);
            int numberOfSearches = wordSearch.size();
            List<String> potentialSqlInjections = sqlInjectionService.findPotentialSqlInjections(wordSearch);

            wordSearches.add(
                    WordSearchDto.builder()
                            .name(searchName)
                            .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                            .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch)
                            .percentOfAllMultiWordSearches(percentOfAllMultiWordSearches)
                            .percentOfLetters(percentOfLetters)
                            .percentOfDigits(percentOfDigits)
                            .numberOfSearches(numberOfSearches)
                            .potentialSqlInjections(potentialSqlInjections)
                            .build()
            );
        }

        return mergeWordSearches(wordSearches);
    }

    private static int getNumberOfAllSearches(Map<Integer, List<String>> separatedStringsByLength) {
        return separatedStringsByLength.values().stream()
                .map(List::size)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<WordSearchDto> mergeWordSearches(List<WordSearchDto> wordSearches) {
        List<WordSearchDto> collect = wordSearches.stream()
                .filter(e -> WordSearchEnum.NINE_WORDS_SEARCH.getWordLength() < e.getAverageNumberOfWordsPerSearch().intValue())
                .collect(Collectors.toList());
        BigDecimal averageNumberOfCharsPerWord = BigDecimal.ZERO;
        BigDecimal averageNumberOfWordsPerSearch = BigDecimal.ZERO;
        BigDecimal percentOfAllMultiWordSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        boolean isCreated = false;
        int totalNumberOfSearches = collect.stream().map(WordSearchDto::getNumberOfSearches).mapToInt(Integer::intValue).sum();

        for (WordSearchDto dto : collect) {
            isCreated = true;
            averageNumberOfCharsPerWord = NumberUtils.calculateAverage(averageNumberOfCharsPerWord, totalNumberOfSearches, dto::getAverageNumberOfCharsPerWord, dto::getNumberOfSearches);
            averageNumberOfWordsPerSearch = NumberUtils.calculateAverage(averageNumberOfWordsPerSearch, totalNumberOfSearches, dto::getAverageNumberOfWordsPerSearch, dto::getNumberOfSearches);
            percentOfAllMultiWordSearches = NumberUtils.calculateAverage(percentOfAllMultiWordSearches, totalNumberOfSearches, dto::getPercentOfAllMultiWordSearches, dto::getNumberOfSearches);
            percentOfLetters = NumberUtils.calculateAverage(percentOfLetters, totalNumberOfSearches, dto::getPercentOfLetters, dto::getNumberOfSearches);
            percentOfDigits = NumberUtils.calculateAverage(percentOfDigits, totalNumberOfSearches, dto::getPercentOfDigits, dto::getNumberOfSearches);
        }

        if (isCreated) {
            WordSearchDto wordSearchDto = WordSearchDto.builder()
                    .name(WordSearchEnum.MORE_THAN_NINE_WORD_SEARCH)
                    .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                    .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch)
                    .percentOfAllMultiWordSearches(percentOfAllMultiWordSearches)
                    .percentOfLetters(percentOfLetters)
                    .percentOfDigits(percentOfDigits)
                    .numberOfSearches(totalNumberOfSearches)
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
