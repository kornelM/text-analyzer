package com.text.analyzer.html;

import com.text.analyzer.file.SimpleFileReader;
import lombok.Getter;

@Getter
public class ScriptService {

    private final String scriptTemplate;

    public ScriptService() {
        this.scriptTemplate = new SimpleFileReader().readToString("/home/kornel/IdeaProjects/text-analyzer/text-analyzer-core/src/main/resources/html/chart_script_template");
    }
}
