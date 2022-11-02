package com.text.analyzer.response;

import com.text.analyzer.response.pojo.LetterSearch;
import com.text.analyzer.response.pojo.WordSearchResult;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@ToString
@SuperBuilder
public class SingleWordSearchResult extends WordSearchResult {
    private List<LetterSearch> letterSearches;
}
