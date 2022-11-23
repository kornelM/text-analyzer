package com.text.analyzer.html.charts;

import com.text.analyzer.response.pojo.AnalyzeResult;
import j2html.tags.DomContent;

import java.util.List;

public interface ChartService {

    List<DomContent> createChart(AnalyzeResult analyzeResult);
}
