package com.text.analyzer.word;

import com.text.analyzer.pojo.SearchType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class WordSeparator {

    public static final String STRING_SPACE = " ";

    public static Map<SearchType, List<String>> separate(List<String> searches) {
        Map<SearchType, List<String>> separatedSearches = new HashMap<>();

        List<String> single = new ArrayList<>();
        List<String> multi = new ArrayList<>();

        for (String search : searches) {
            String trimmedSearch = search.trim();
            if (trimmedSearch.split(STRING_SPACE).length > 1) {
                if (isNull(separatedSearches.get(SearchType.MULTI))) {
                    multi.add(trimmedSearch);
                    separatedSearches.put(SearchType.MULTI, multi);
                } else {
                    separatedSearches.get(SearchType.MULTI).add(trimmedSearch);
                }
            } else {
                if (isNull(separatedSearches.get(SearchType.SINGLE))) {
                    single.add(trimmedSearch);
                    separatedSearches.put(SearchType.SINGLE, single);
                } else {
                    separatedSearches.get(SearchType.SINGLE).add(trimmedSearch);
                }
            }
        }
        return separatedSearches;
    }
}
