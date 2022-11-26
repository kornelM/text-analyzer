package com.text.analyzer.data.reader;

import java.util.List;

public interface AnalyzerFileReader {

    List<String> readToList(String path);

    String readToString(String path);
}
