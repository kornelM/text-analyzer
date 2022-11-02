package com.text.analyzer;

import com.google.gson.Gson;
import com.text.analyzer.common.dto.SearchResultDto;
import com.text.analyzer.configuration.ConfigProperty;
import com.text.analyzer.configuration.PropertiesLoader;
import com.text.analyzer.file.DirectoryUtils;
import com.text.analyzer.file.SimpleFileReader;
import com.text.analyzer.multi.dto.MultiWordSearchDto;
import com.text.analyzer.multi.dto.WordSearchDto;
import com.text.analyzer.multi.service.impl.MultiWordSearchServiceImpl;
import com.text.analyzer.pojo.SearchType;
import com.text.analyzer.single.dto.LetterSearchDto;
import com.text.analyzer.single.dto.SingleWordSearchDto;
import com.text.analyzer.single.service.impl.SingleWordSearchServiceImpl;
import com.text.analyzer.word.WordSeparator;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class TextAnalyzer {

    public static void main(String[] args) throws IOException {
        SimpleFileReader simpleFileReader = new SimpleFileReader();
        SingleWordSearchServiceImpl singleWordSearchService = new SingleWordSearchServiceImpl();
        MultiWordSearchServiceImpl multiWordSearchService = new MultiWordSearchServiceImpl();

        Properties conf = PropertiesLoader.loadProperties();
        String dirPath = conf.getProperty(ConfigProperty.PROPERTY_TEXT_FILES_DIRECTORY.getValue());

        List<SingleWordSearchDto> singleWordSearchDtos = new ArrayList<>();
        List<MultiWordSearchDto> multiWordSearchDtos = new ArrayList<>();
        List<String> files = DirectoryUtils.listFilesUsingFilesList(dirPath);

        List<String> searches = null;
        for (String filePath : files) {
            searches = simpleFileReader.readToList(filePath);
            Map<SearchType, List<String>> separatedSearches = WordSeparator.separate(searches);
            singleWordSearchDtos.add(singleWordSearchService.processSearches(separatedSearches.get(SearchType.SINGLE)));
            multiWordSearchDtos.add(multiWordSearchService.processSearches(separatedSearches.get(SearchType.MULTI)));
        }

        int totalNumberOfSingleSearches = singleWordSearchDtos.stream()
                .map(SearchResultDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();

        int totalNumberOfMultiSearches = multiWordSearchDtos.stream()
                .map(SearchResultDto::getNumberOfSearches)
                .mapToInt(Integer::intValue)
                .sum();

        List<LetterSearchDto> collect = singleWordSearchDtos.stream()
                .flatMap(s -> s.getLetterSearches().stream())
                .collect(Collectors.toList());
        Map<String, List<LetterSearchDto>> sorted = collect.stream().collect(Collectors.groupingBy(SearchResultDto::getName));
        List<LetterSearchDto> collect1 = sorted.values().stream()
                .map(TextAnalyzer::mergeLetters)
                .collect(Collectors.toList());

        List<WordSearchDto> collectWord = multiWordSearchDtos.stream()
                .flatMap(s -> s.getWordSearches().stream())
                .collect(Collectors.toList());
        Map<String, List<WordSearchDto>> sortedWord = collectWord.stream().collect(Collectors.groupingBy(SearchResultDto::getName));
        List<WordSearchDto> collectWord1 = sortedWord.values().stream()
                .map(TextAnalyzer::mergeWord)
                .collect(Collectors.toList());

        Gson gson = new Gson();

        String json = gson.toJson(singleWordSearchDtos);
        String json2 = gson.toJson(multiWordSearchDtos);

        System.out.println(json);
        System.out.println("________________________");
        System.out.println(json2);
    }

    private static WordSearchDto mergeWord(List<WordSearchDto> wordSearchDtos) {
        int numberOfSearches = 0;
        BigDecimal averageNumberOfCharsPerWord = BigDecimal.ZERO;
        BigDecimal averageNumberOfWordsPerSearch = BigDecimal.ZERO;
        BigDecimal percentOfAllMultiWordsSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        for (WordSearchDto wordSearchDto : wordSearchDtos) {
            numberOfSearches += wordSearchDto.getNumberOfSearches();
            averageNumberOfCharsPerWord = averageNumberOfCharsPerWord.add(wordSearchDto.getAverageNumberOfCharsPerWord());
            averageNumberOfWordsPerSearch = averageNumberOfWordsPerSearch.add(wordSearchDto.getAverageNumberOfWordsPerSearch());
            percentOfAllMultiWordsSearches = percentOfAllMultiWordsSearches.add(wordSearchDto.getPercentOfAllMultiWordsSearches());
            percentOfLetters = percentOfLetters.add(wordSearchDto.getPercentOfLetters());
            percentOfDigits = percentOfDigits.add(wordSearchDto.getPercentOfDigits());
        }

        BigDecimal numberOfDtos = BigDecimal.valueOf(wordSearchDtos.size());
        return WordSearchDto.builder()
                .name(wordSearchDtos.get(0).getName())
                .averageNumberOfCharsPerWord(averageNumberOfCharsPerWord.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .averageNumberOfWordsPerSearch(averageNumberOfWordsPerSearch.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfAllMultiWordsSearches(percentOfAllMultiWordsSearches.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfLetters(percentOfLetters.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfDigits(percentOfDigits.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .numberOfSearches(numberOfSearches)
                .build();
    }

    private static LetterSearchDto mergeLetters(List<LetterSearchDto> list) {
        int numberOfSearches = 0;
        BigDecimal percentOfAllOneWordSearches = BigDecimal.ZERO;
        BigDecimal percentOfLetters = BigDecimal.ZERO;
        BigDecimal percentOfDigits = BigDecimal.ZERO;
        BigDecimal numberOfDtos = BigDecimal.valueOf(list.size());

        for (LetterSearchDto dto : list) {
            numberOfSearches += dto.getNumberOfSearches();
            percentOfAllOneWordSearches = percentOfAllOneWordSearches.add(dto.getPercentOfAllOneWordSearches());
            percentOfLetters = percentOfLetters.add(dto.getPercentOfLetters());
            percentOfDigits = percentOfDigits.add(dto.getPercentOfDigits());
        }

        return LetterSearchDto.builder()
                .name(list.get(0).getName())
                .numberOfSearches(numberOfSearches)
                .percentOfAllOneWordSearches(percentOfAllOneWordSearches.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfLetters(percentOfLetters.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .percentOfDigits(percentOfDigits.divide(numberOfDtos, RoundingMode.HALF_EVEN))
                .build();
    }

}
