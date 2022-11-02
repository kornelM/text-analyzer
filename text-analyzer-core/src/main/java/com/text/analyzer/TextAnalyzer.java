package com.text.analyzer;

import com.google.gson.Gson;
import com.text.analyzer.configuration.ConfigProperty;
import com.text.analyzer.configuration.PropertiesLoader;
import com.text.analyzer.file.DirectoryUtils;
import com.text.analyzer.file.SimpleFileReader;
import com.text.analyzer.multi.dto.MultiWordSearchDto;
import com.text.analyzer.multi.service.impl.MultiWordSearchServiceImpl;
import com.text.analyzer.pojo.SearchType;
import com.text.analyzer.single.dto.SingleWordSearchDto;
import com.text.analyzer.single.service.impl.SingleWordSearchServiceImpl;
import com.text.analyzer.word.WordSeparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

        for (String filePath : files) {
            List<String> searches = simpleFileReader.readToList(filePath);
            Map<SearchType, List<String>> separatedSearches = WordSeparator.separate(searches);
            singleWordSearchDtos.add(singleWordSearchService.processSearches(separatedSearches.get(SearchType.SINGLE)));
            multiWordSearchDtos.add(multiWordSearchService.processSearches(separatedSearches.get(SearchType.MULTI)));
        }

        Gson gson = new Gson();

        String json = gson.toJson(singleWordSearchDtos);
        String json2 = gson.toJson(multiWordSearchDtos);

        System.out.println(json);
        System.out.println("________________________");
        System.out.println(json2);
    }

}
