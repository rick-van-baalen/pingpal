package com.pingpal.views.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.pingpal.components.Placeholder;
import com.pingpal.helpers.JsonFormatter;
import com.webforj.component.html.elements.Div;
import com.webforj.component.html.elements.Paragraph;

public class ResponseBody extends Div {

    private Paragraph paragraph;
    private Placeholder placeholder;

    public ResponseBody() {
        setWidth("100%");
        setHeight("100%");
        setStyle("overflow-x", "auto");
        setStyle("word-wrap", "break-word");

        paragraph = new Paragraph();
        paragraph.setVisible(false);

        placeholder = new Placeholder("No response body available.");
        add(paragraph, placeholder);
    }

    public void setData(String response) {
        placeholder.setVisible(false);
        paragraph.setVisible(true);

        try {
            JsonElement jsonElement = JsonParser.parseString(response);
            if (JsonFormatter.format(jsonElement).equals("null")) {
                paragraph.setHtml("");
            } else {
                paragraph.setHtml("<pre>" + JsonFormatter.format(jsonElement) + "</pre>");
            }
        } catch (Exception e) {
            paragraph.setHtml(response);
        }
    }

    public void clear() {
        paragraph.setText("");
        paragraph.setVisible(false);
        placeholder.setVisible(true);
    }
    
}