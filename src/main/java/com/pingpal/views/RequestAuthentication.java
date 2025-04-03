package com.pingpal.views;

import java.util.HashMap;

import com.webforj.component.Expanse;
import com.webforj.component.field.TextArea;
import com.webforj.component.field.TextField;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.list.ChoiceBox;
import com.webforj.component.list.event.ListSelectEvent;

public class RequestAuthentication extends Div {
    
    private ChoiceBox authType;
    private TextField key;
    private TextArea value;

    public RequestAuthentication() {
        setWidth("100%");
        setHeight("100%");

        FlexLayout layout = new FlexLayout();
        layout.setJustifyContent(FlexJustifyContent.BETWEEN);
        layout.setSpacing("10px");
        layout.setWidth("100%");
        add(layout);

        Div left = new Div().setWidth("100%");
        left.setMaxWidth("400px");

        authType = new ChoiceBox("Auth Type");
        authType.add("NO_AUTH", "No Auth");
        authType.add("API_KEY", "API Key");
        authType.selectIndex(0);
        authType.setWidth("100%");
        authType.setExpanse(Expanse.LARGE);
        authType.onSelect(this::onTypeChange);
        left.add(authType);

        FlexLayout right = new FlexLayout();
        right.setDirection(FlexDirection.COLUMN);
        right.setSpacing("10px");
        right.setWidth("100%");

        key = new TextField("Key");
        key.setValue("Authorization");
        key.setExpanse(Expanse.LARGE);
        key.setVisible(false);

        value = new TextArea("Value");
        value.setRows(3);
        value.setValue("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MDAwMDEiLCJhZG1pbiI6IlJWQiIsImlhdCI6MTcyMTk4MDE0OH0.89R__B1YJNTUzNCsg93h-ZcbsSlKHIhmxoQr-iKoVAo");
        value.setExpanse(Expanse.LARGE);
        value.setVisible(false);

        right.add(key, value);

        layout.add(left, right);
    }

    private void onTypeChange(ListSelectEvent event) {
        String type = event.getSelectedItem().getKey().toString();
        switch (type) {
            case "API_KEY":
                key.setVisible(true);
                value.setVisible(true);
                break;
            default:
                key.setVisible(false);
                value.setVisible(false);
                break;
        }
    }

    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("AUTH_TYPE", authType.getSelectedKey().toString());
        data.put("KEY", key.getValue().trim());
        data.put("VALUE", value.getValue().trim());
        return data;
    }

}
