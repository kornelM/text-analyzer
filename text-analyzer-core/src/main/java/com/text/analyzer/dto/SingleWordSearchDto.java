package com.text.analyzer.dto;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@ToString
@SuperBuilder
public class SingleWordSearchDto extends WordSearchResultDto {
    private List<LetterSearchDto> letterSearches;
}
