package com.text.analyzer.response.pojo;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class SqlInjectionPhrases {

    private int totalNumber;
    private String phrase;
}
