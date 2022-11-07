package com.text.analyzer.response;

import com.text.analyzer.response.pojo.WordSearch;
import com.text.analyzer.response.pojo.WordSearchResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MultiWordSearchResult extends WordSearchResult {

    private List<WordSearch> wordsSearches;
    private List<String> potentialSqlInjections;
}
