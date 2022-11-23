package com.text.analyzer.search.word;

import com.text.analyzer.common.utils.StringUtils;
import com.text.analyzer.pojo.SearchType;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class WordSeparator {

    public static Map<SearchType, List<String>> separate(List<String> searches) {
        return searches.stream()
                .map(String::trim)
                .collect(Collectors.groupingBy(
                                s -> s.split(StringUtils.SPACE).length > 1 ? SearchType.MULTI : SearchType.SINGLE,
                                HashMap::new,
                                toList()
                        )
                );
    }
}
