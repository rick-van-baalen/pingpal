package com.pingpal.views;

import java.net.http.HttpHeaders;

import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.Paragraph;

public class ResponseHeaders extends Div {

    private Paragraph text;

    public ResponseHeaders() {
        setWidth("100%");
        setHeight("100%");
        setStyle("overflow-x", "auto");
        setStyle("word-wrap", "break-word");

        text = new Paragraph();
        add(text);
    }

    public void setData(HttpHeaders response) {
        StringBuilder content = new StringBuilder();
        
        response.map().forEach((name, values) -> {
            String value = String.join(", ", values);
            content.append(name + ": " + value + "<br>");
        });
        
        text.setHtml(content.toString());
    }

}
