package com.pingpal.views.authentication;

import java.util.HashMap;

import com.pingpal.helpers.IAuthenticationType;
import com.webforj.component.Composite;
import com.webforj.component.Expanse;
import com.webforj.component.field.TextArea;
import com.webforj.component.field.TextField;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class ApiKeyAuthentication extends Composite<FlexLayout> implements IAuthenticationType {

    private FlexLayout self = getBoundComponent();
    private TextField key;
    private TextArea value;

    public ApiKeyAuthentication() {
        self.setWidth("100%");
        self.setHeight("100%");
        self.setDirection(FlexDirection.COLUMN);
        self.setSpacing("10px");

        key = new TextField("Key");
        key.setValue("Authorization");
        key.setExpanse(Expanse.LARGE);

        value = new TextArea("Value");
        value.setRows(3);
        value.setValue("");
        value.setExpanse(Expanse.LARGE);

        self.add(key, value);
    }

    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("AUTH_TYPE", "API_KEY");
        data.put("KEY", key.getText().trim());
        data.put("VALUE", value.getText().trim());
        return data;
    }

    @Override
    public void setVisible(Boolean visible) {
        self.setVisible(visible);
    }
    
}
