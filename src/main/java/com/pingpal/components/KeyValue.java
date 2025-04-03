package com.pingpal.components;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.UUID;

import com.webforj.component.field.TextField;
import com.webforj.component.html.elements.Div;
import com.webforj.component.icons.Icon;
import com.webforj.component.icons.IconButton;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class KeyValue extends Div {

    private FlexLayout layout;
    private LinkedHashMap<String, TextField> keys = new LinkedHashMap<String, TextField>();
    private LinkedHashMap<String, TextField> values = new LinkedHashMap<String, TextField>();

    public KeyValue() {
        layout = new FlexLayout();
        layout.setDirection(FlexDirection.COLUMN);
        add(layout);

        createRow();
    }

    private void createRow() {
        String uuid = UUID.randomUUID().toString();

        FlexLayout row = new FlexLayout();
        row.setAlignment(FlexAlignment.CENTER);
        row.setJustifyContent(FlexJustifyContent.BETWEEN);

        TextField key = new TextField();
        key.setPlaceholder("Key");
        key.setWidth("100%");
        keys.put(uuid, key);

        TextField value = new TextField();
        value.setPlaceholder("Value");
        value.setWidth("100%");
        values.put(uuid, value);

        Icon icon = TablerIcon.create("trash");
        IconButton button = new IconButton(icon);
        button.onClick(e -> deleteRow(row, uuid));

        row.add(key, value, button);

        layout.add(row);

        key.onBlur(e -> checkNewRow(key, value));
        value.onBlur(e -> checkNewRow(key, value));
        
        key.focus();
    }

    private void deleteRow(FlexLayout row, String uuid) {
        if (keys.size() == 1) return;

        row.destroy();
        keys.remove(uuid);
        values.remove(uuid);
    }

    private void checkNewRow(TextField key, TextField value) {
        TextField lastKeyField = keys.lastEntry().getValue();
        TextField lastValueField = values.lastEntry().getValue();

        if (!lastKeyField.getValue().trim().isBlank() && !lastValueField.getValue().trim().isBlank()) {
            createRow();
        }
    }

    public HashMap<String, String> getData() {
        HashMap<String, String> params = new HashMap<String, String>();

        Iterator<String> it = keys.keySet().iterator();
        while (it.hasNext()) {
            String uuid = it.next();

            String key = keys.get(uuid).getText().trim();
            String value = values.get(uuid).getText().trim();
            if (key.isBlank() || value.isBlank()) continue;

            params.put(key, value);
        }

        return params;
    }
    
}
