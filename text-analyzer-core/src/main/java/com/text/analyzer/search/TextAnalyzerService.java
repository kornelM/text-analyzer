package com.text.analyzer.search;

import com.text.analyzer.configuration.ConfigProperty;
import com.text.analyzer.configuration.PropertyLoader;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextAnalyzerService {
    private final SimpleFileReader simpleFileReader;
    private final SingleWordSearchProcessor singleWordSearchService;
    private final MultiWordSearchProcessor multiWordSearchService;
    private final PropertyLoader propertyLoader;
    private final AnalyzeResultFactory analyzeResultFactory;

    public TextAnalyzerService() {
        this.simpleFileReader = new SimpleFileReader();
        this.singleWordSearchService = new SingleWordSearchProcessor();
        this.multiWordSearchService = new MultiWordSearchProcessor();
        this.propertyLoader = new PropertyLoader();
        this.analyzeResultFactory = new AnalyzeResultFactory();
    }

    public AnalyzeResult analyzeTextSearches() {
        String dirPath = propertyLoader.getProperty(ConfigProperty.PROPERTY_TEXT_FILES_DIRECTORY);

        List<SingleWordSearchDto> singleWordSearchDtos = new ArrayList<>();
        List<MultiWordSearchDto> multiWordSearchDtos = new ArrayList<>();

        for (String filePath : DirectoryUtils.listFilesInDir(dirPath)) {
            List<String> searches = simpleFileReader.readToList(filePath);
            Map<SearchType, List<String>> separatedSearches = WordSeparator.separate(searches);
            singleWordSearchDtos.add(singleWordSearchService.processSearches(separatedSearches.get(SearchType.SINGLE)));
            multiWordSearchDtos.add(multiWordSearchService.processSearches(separatedSearches.get(SearchType.MULTI)));
        }

        return analyzeResultFactory.buildAnalyzeResult(singleWordSearchDtos, multiWordSearchDtos);
    }
}
