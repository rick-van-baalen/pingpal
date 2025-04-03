package com.pingpal.views;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.pingpal.helpers.JsonFormatter;
import com.webforj.component.Theme;
import com.webforj.component.button.Button;
import com.webforj.component.field.TextArea;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.toast.Toast;

public class RequestBody extends Div {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private TextArea area;

    public RequestBody() {
        setWidth("100%");
        setHeight("100%");

        FlexLayout layout = new FlexLayout();
        layout.setDirection(FlexDirection.COLUMN);
        layout.setSpacing("10px");
        layout.setWidth("100%");
        layout.setHeight("100%");
        add(layout);

        FlexLayout top = new FlexLayout();
        top.setAlignment(FlexAlignment.CENTER);
        top.setSpacing("10px");

        Div bottom = new Div();
        bottom.setHeight("100%");
        layout.add(top, bottom);

        Button beautify = new Button("Beautify");
        beautify.onClick(e -> doBeautify());

        Button clear = new Button("Clear");
        clear.onClick(e -> doClear());
        
        top.add(beautify, clear);

        area = new TextArea();
        area.setWidth("100%");
        area.setHeight("100%");
        bottom.add(area);
    }

    private void doBeautify() {
        try {
            if (area.getText().trim().isBlank()) return;

            JsonElement jsonElement = JsonParser.parseString(area.getText().trim());
            area.setText(JsonFormatter.format(jsonElement));
            area.setInvalid(false);
        } catch (Exception e) {
            Toast.show("Parsing the JSON has failed: " + e.getMessage(), Theme.DANGER);
            area.setInvalid(true);
        }
    }

    private void doClear() {
        area.setText("");
    }

    public String getData() {
        return area.getText().trim();
    }
    
}
