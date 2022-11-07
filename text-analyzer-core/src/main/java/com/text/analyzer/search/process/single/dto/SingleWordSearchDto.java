package com.text.analyzer.search.process.single.dto;

import com.text.analyzer.common.dto.WordSearchResultDto;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class SingleWordSearchDto extends WordSearchResultDto {
    private List<LetterSearchDto> letterSearches;
}
