package com.text.analyzer.html;

import com.text.analyzer.response.pojo.AnalyzeResult;
import org.junit.jupiter.api.Test;

class HtmlCreatorTest {

    @Test
    public void test() {
        HtmlCreator htmlCreator = new HtmlCreator();

        //when
        htmlCreator.createHtml(new AnalyzeResult());
    }

}
