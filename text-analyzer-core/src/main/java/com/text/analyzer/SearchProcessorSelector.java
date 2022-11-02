//package com.text.analyzer;
//
//import com.text.analyzer.multi.service.impl.MultiWordSearchServiceImpl;
//import com.text.analyzer.pojo.SearchType;
//import com.text.analyzer.single.service.WordSearchService;
//import com.text.analyzer.single.service.impl.SingleWordSearchServiceImpl;
//
//import java.util.Map;
//
//import static java.util.Objects.nonNull;
//
//public class SearchProcessorSelector {
//
//    private final Map<SearchType, WordSearchService> map;
//
//    public SearchProcessorSelector() {
//
//        this.map = Map.of(
//                singleWordSearchService.getType(), singleWordSearchService,
//                multiWordSearchService.getType(), multiWordSearchService
//        );
//    }
//
//    public WordSearchService getSearchProcessor(SearchType searchType) {
//        WordSearchService wordSearchService = map.get(searchType);
//
//        if (nonNull(wordSearchService)) {
//            return wordSearchService;
//        }
//
//        throw new RuntimeException("Type " + searchType + " not found!");
//    }
//}
