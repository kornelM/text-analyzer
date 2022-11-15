package com.text.analyzer.html.charts.impl;

import com.text.analyzer.html.ChartName;
import com.text.analyzer.html.charts.AbstractChartService;
import com.text.analyzer.html.charts.ChartService;
import com.text.analyzer.response.pojo.AnalyzeResult;
import j2html.tags.DomContent;

import java.math.BigDecimal;
import java.util.List;

public class MainChartService extends AbstractChartService implements ChartService {

    @Override
    public List<DomContent> createChart(AnalyzeResult analyzeResult) {
        BigDecimal percentOfAllSingle = analyzeResult.getSingleWordSearchResult().getPercentOfAll();
        BigDecimal percentOfAllMulti = analyzeResult.getMultiWordSearchResult().getPercentOfAll();

        List<String> arguments = List.of(
                analyzeResult.getSingleWordSearchResult().getName().name(),
                analyzeResult.getMultiWordSearchResult().getName().name()
        );
        List<String> values = List.of(percentOfAllSingle.toString(), percentOfAllMulti.toString());

        return getDomContents(arguments, values);
    }


    @Override
    public String getChartId() {
        return ChartName.SINGLE_AND_MULTI_SEARCHES.name();
    }

    @Override
    protected String getChartTitle() {
        return ChartName.SINGLE_AND_MULTI_SEARCHES.getChartTitle();
    }
}
