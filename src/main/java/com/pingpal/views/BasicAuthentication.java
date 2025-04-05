package com.pingpal.views;

import java.util.HashMap;

import com.pingpal.helpers.IAuthenticationType;
import com.webforj.component.Composite;
import com.webforj.component.Expanse;
import com.webforj.component.field.PasswordField;
import com.webforj.component.field.TextField;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class BasicAuthentication extends Composite<FlexLayout> implements IAuthenticationType {

    private FlexLayout self = getBoundComponent();
    private TextField username;
    private PasswordField password;

    public BasicAuthentication() {
        self.setWidth("100%");
        self.setHeight("100%");
        self.setDirection(FlexDirection.COLUMN);
        self.setSpacing("10px");

        username = new TextField("Username");
        username.setExpanse(Expanse.LARGE);

        password = new PasswordField("Password");
        password.setExpanse(Expanse.LARGE);

        self.add(username, password);
    }

    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("AUTH_TYPE", "BASIC");
        data.put("USERNAME", username.getText().trim());
        data.put("PASSWORD", password.getText().trim());
        return data;
    }

    @Override
    public void setVisible(Boolean visible) {
        self.setVisible(visible);
    }
    
}
