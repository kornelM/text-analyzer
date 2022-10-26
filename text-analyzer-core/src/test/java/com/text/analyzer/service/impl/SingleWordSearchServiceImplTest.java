package com.text.analyzer.service.impl;


import com.text.analyzer.dto.SingleWordSearchDto;
import com.text.analyzer.pojo.LetterNumberEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SingleWordSearchServiceImplTest {

    @Test
    void test() {
        //given
        Random random = new Random();

        List<String> strings = new ArrayList<>();

        for (int i = 0; i < 10_000; i++) {
            strings.add(String.valueOf(random.nextInt(1100_000_000)));
        }

        SingleWordSearchServiceImpl singleWordSearchService = new SingleWordSearchServiceImpl();
        //when
        long start = System.currentTimeMillis();
        SingleWordSearchDto singleWordSearchDto = singleWordSearchService.processSearches(strings);
        System.out.println("singleWordSearchDto: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
//        Map<Integer, Set<String>> strings1 = singleWordSearchService.getStringsToIntNonConcurrent(strings);
        System.out.println("getStringsToIntNonConcurrent: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        Map<LetterNumberEnum, Set<String>> strings2 = singleWordSearchService.getStringsToEnumNonConcurrent(strings);
        System.out.println("getStringsToEnumNonConcurrent: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        Map<Integer, Set<String>> strings3 = singleWordSearchService.getStringsToIntConcurrent(strings);
        System.out.println("getStringsToIntConcurrent: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        Map<LetterNumberEnum, Set<String>> strings4 = singleWordSearchService.getStringsToEnumConcurrent(strings);
        System.out.println("getStringsToEnumConcurrent: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void test1() {
        SingleWordSearchServiceImpl singleWordSearchService = new SingleWordSearchServiceImpl();

        Set<String> strings = Set.of("one11", "one2", "one3", "one4", "one5", "one6");

        BigDecimal percentOfNumbersInLetters = BigDecimal.valueOf(singleWordSearchService.getPercentOfNumbersInLetters(strings)).setScale(2, RoundingMode.UP);
        System.out.println(percentOfNumbersInLetters);
    }
}
