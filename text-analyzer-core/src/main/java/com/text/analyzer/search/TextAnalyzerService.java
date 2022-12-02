package com.text.analyzer.search;

import com.text.analyzer.data.reader.SimpleFileReader;
import com.text.analyzer.data.utils.DirectoryUtils;
import com.text.analyzer.pojo.SearchType;
import com.text.analyzer.response.pojo.AnalyzeResult;
import com.text.analyzer.search.process.multi.dto.MultiWordSearchDto;
import com.text.analyzer.search.process.multi.processor.MultiWordSearchProcessor;
import com.text.analyzer.search.process.single.dto.SingleWordSearchDto;
import com.text.analyzer.search.process.single.processor.SingleWordSearchProcessor;
import com.text.analyzer.search.result.factory.AnalyzeResultFactory;
import com.text.analyzer.search.word.WordSeparator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class TextAnalyzerService {
    private final SimpleFileReader simpleFileReader;
    private final SingleWordSearchProcessor singleWordSearchService;
    private final MultiWordSearchProcessor multiWordSearchService;
    private final AnalyzeResultFactory analyzeResultFactory;
    private final int mostSearchedPhrasesLimit;

    public TextAnalyzerService(String mostSearchedPhrasesLimit) {
        this.simpleFileReader = new SimpleFileReader();
        this.singleWordSearchService = new SingleWordSearchProcessor();
        this.multiWordSearchService = new MultiWordSearchProcessor();
        this.analyzeResultFactory = new AnalyzeResultFactory();
        this.mostSearchedPhrasesLimit = Integer.parseInt(mostSearchedPhrasesLimit);
    }

    public AnalyzeResult analyzeTextSearches(String dirPath) {
        List<SingleWordSearchDto> singleWordSearchDtos = new ArrayList<>();
        List<MultiWordSearchDto> multiWordSearchDtos = new ArrayList<>();
        List<Map<String, Long>> mostSearchedPhrases = new ArrayList<>();

        for (String filePath : DirectoryUtils.listFilesInDir(dirPath)) {
            long start = System.currentTimeMillis();
            List<String> searches = simpleFileReader.readAsList(filePath);
            Map<SearchType, List<String>> separatedSearches = WordSeparator.separate(searches);
            singleWordSearchDtos.add(singleWordSearchService.processSearches(separatedSearches.get(SearchType.SINGLE)));
            multiWordSearchDtos.add(multiWordSearchService.processSearches(separatedSearches.get(SearchType.MULTI)));
            log.info("Processing file: {} took: {} ms", filePath, System.currentTimeMillis() - start);

            mostSearchedPhrases.add(countSearches(separatedSearches));
        }

        AnalyzeResult analyzeResult = analyzeResultFactory.buildAnalyzeResult(singleWordSearchDtos, multiWordSearchDtos);
        analyzeResult.setMostSearchPhrases(getMostSearchedPhrases(mostSearchedPhrases));
        return analyzeResult;
    }

    private Map<String, Long> getMostSearchedPhrases(List<Map<String, Long>> searches) {
        return searches.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(mostSearchedPhrasesLimit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Map<String, Long> countSearches(Map<SearchType, List<String>> separatedSearches) {
        Collection<List<String>> values = separatedSearches.values();
        List<String> collect = values.stream()
                .flatMap(Collection::stream)
                .map(StringUtils::stripAccents)
                .map(s -> Normalizer.normalize(s, Normalizer.Form.NFKD))
                .collect(Collectors.toList());

        return collect.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
