package com.text.analyzer.multi.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class MultiWordSearchDto extends WordSearchResultDto {
    private List<WordSearchDto> wordSearches;
}
