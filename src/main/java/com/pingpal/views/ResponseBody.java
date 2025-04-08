package com.pingpal.views;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.pingpal.components.Placeholder;
import com.pingpal.helpers.JsonFormatter;
import com.webforj.component.html.elements.Div;

public class ResponseBody extends Div {

    private Placeholder placeholder;

    public ResponseBody() {
        setWidth("100%");
        setHeight("100%");
        setStyle("overflow-x", "auto");
        setStyle("word-wrap", "break-word");

        placeholder = new Placeholder("No response body available.");
        add(placeholder);
    }

    public void setData(String response) {
        placeholder.setVisible(false);

        try {
            JsonElement jsonElement = JsonParser.parseString(response);
            setHtml("<pre>" + JsonFormatter.format(jsonElement) + "</pre>");
        } catch (Exception e) {
            setHtml(response);
        }
    }
    
}