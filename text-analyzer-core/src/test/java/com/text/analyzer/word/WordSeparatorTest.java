package com.text.analyzer.word;

import com.text.analyzer.pojo.SearchType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordSeparatorTest {

    @Test
    public void shouldReturnMapOfSplitSearchesBasedOnWordsNumber() {
        //given
        String single = "single ";
        String singleTwo = " single_two";
        String multiOne = "multi one";
        String multiTwentyOne = "multi twenty one";

        List<String> given = List.of(
                single,
                singleTwo,
                multiOne,
                multiTwentyOne
        );

        Map<SearchType, List<String>> expected = Map.of(
                SearchType.SINGLE, List.of(single.trim(), singleTwo.trim()),
                SearchType.MULTI, List.of(multiOne, multiTwentyOne)
        );

        //when
        Map<SearchType, List<String>> returned = WordSeparator.separate(given);

        //then
        assertEquals(expected, returned);
    }
}
