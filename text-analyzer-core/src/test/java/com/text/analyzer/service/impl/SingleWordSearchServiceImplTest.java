package com.text.analyzer.service.impl;


public class SingleWordSearchServiceImplTest {

//    @Test
//    void test() throws JsonProcessingException {
//        //given
//        Random random = new Random();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        List<String> strings = new ArrayList<>();
//
//        for (int i = 0; i < 10_000; i++) {
//            strings.add(String.valueOf(random.nextInt(1100_000_000)));
//        }
//
//        SingleWordSearchServiceImpl singleWordSearchService = new SingleWordSearchServiceImpl();
//        //when
//        long start = System.currentTimeMillis();
//        SingleWordSearchDto singleWordSearchDto = singleWordSearchService.processSearches(strings);
//        System.out.println("singleWordSearchDto: " + (System.currentTimeMillis() - start) + "ms");
//        System.out.println(objectMapper.writeValueAsString(singleWordSearchDto));
//        start = System.currentTimeMillis();
////        Map<Integer, Set<String>> strings1 = singleWordSearchService.getStringsToIntNonConcurrent(strings);
//        System.out.println("getStringsToIntNonConcurrent: " + (System.currentTimeMillis() - start) + "ms");
//
//        start = System.currentTimeMillis();
//        Map<LetterNumberEnum, Set<String>> strings2 = singleWordSearchService.getStringsToEnumNonConcurrent(strings);
//        System.out.println("getStringsToEnumNonConcurrent: " + (System.currentTimeMillis() - start) + "ms");
//
//        start = System.currentTimeMillis();
//        Map<Integer, Set<String>> strings3 = singleWordSearchService.getStringsToIntConcurrent(strings);
//        System.out.println("getStringsToIntConcurrent: " + (System.currentTimeMillis() - start) + "ms");
//
//        start = System.currentTimeMillis();
//        Map<LetterNumberEnum, Set<String>> strings4 = singleWordSearchService.getStringsToEnumConcurrent(strings);
//        System.out.println("getStringsToEnumConcurrent: " + (System.currentTimeMillis() - start) + "ms");
//    }
}
