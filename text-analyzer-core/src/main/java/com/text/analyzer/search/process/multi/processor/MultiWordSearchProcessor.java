package com.text.analyzer.search.process.multi.processor;

import com.text.analyzer.response.pojo.SearchName;
import com.text.analyzer.search.process.WordSearchProcessor;
import com.text.analyzer.search.process.multi.creator.MultiWordSearchCreatorImpl;
import com.text.analyzer.search.process.multi.creator.WordsSearchCreator;
import com.text.analyzer.search.process.multi.creator.service.MultiWordAverageService;
import com.text.analyzer.search.process.multi.creator.service.StatsService;
import com.text.analyzer.search.process.multi.creator.service.impl.MultiWordAverageServiceImpl;
import com.text.analyzer.search.process.multi.creator.service.impl.StatsServiceImpl;
import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;
import com.text.analyzer.search.process.multi.dto.WordSearchDto;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class MultiWordSearchProcessor implements WordSearchProcessor<MultiWordSearchDto> {

    private final WordsSearchCreator wordsSearchCreator;
    private final StatsService statsService;
    private final MultiWordAverageService multiWordAverageService;

    public MultiWordSearchProcessor() {
        this.wordsSearchCreator = new MultiWordSearchCreatorImpl();
        this.statsService = new StatsServiceImpl();
        this.multiWordAverageService = new MultiWordAverageServiceImpl();
    }

    @Override
    public MultiWordSearchDto processSearches(List<String> searches) {
        long start = System.currentTimeMillis();
        List<WordSearchDto> wordSearchDtos = wordsSearchCreator.createWordsSearches(searches);

        BigDecimal averageNumberOfWords = statsService.averageNumberOfWords(wordSearchDtos);
        int theMostWordInSearch = statsService.theMostWordsInSearch(wordSearchDtos);
        int theLeastWords = statsService.theLeastWordsInSearch(wordSearchDtos);
        int numberOfAllSearches = statsService.totalNumberOfSearches(wordSearchDtos);
        BigDecimal averageNumberOfCharsPerWord = multiWordAverageService.averageNumberOfCharsPerWord(searches);

        MultiWordSearchDto multiWordSearchDto = MultiWordSearchDto.builder()
                .name(SearchName.MULTI_WORD_SEARCH)
                .averageNumberOfWords(averageNumberOfWords.intValue())
                .theMostWordInSearch(theMostWordInSearch)
                .theLeastWords(theLeastWords)
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord)
                .numberOfSearches(numberOfAllSearches)
                .wordSearches(wordSearchDtos)
                .build();
        log.info("Processing {} ({} searches) took: {} ms", multiWordSearchDto.getName(), numberOfAllSearches, System.currentTimeMillis() - start);
        return multiWordSearchDto;
    }
}
