package com.text.analyzer.html.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChartName {

    SINGLE_AND_MULTI_SEARCHES("Wykres stosunku zapytań jednosłownych do wielosłownych"),
    LETTERS_SEARCHES("Wykres zapytań jednosłownych"),
    WORDS_SEARCHES("Wykres zapytań wielosłownych"),
    MAIN_INFO("Ogólne dane");

    private final String chartTitle;
}
