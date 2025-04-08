package com.pingpal.views;

import java.util.HashMap;

import com.pingpal.components.Placeholder;
import com.pingpal.helpers.IAuthenticationType;
import com.webforj.component.Expanse;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.list.ChoiceBox;
import com.webforj.component.list.event.ListSelectEvent;

public class RequestAuthentication extends Div {
    
    private FlexLayout right;
    private ChoiceBox authType;
    private Placeholder placeholder;
    private ApiKeyAuthentication apiKeyAuthentication;
    private BasicAuthentication basicAuthentication;
    private IAuthenticationType activeAuth;

    public RequestAuthentication() {
        setWidth("100%");
        setHeight("100%");

        FlexLayout layout = new FlexLayout();
        layout.setJustifyContent(FlexJustifyContent.BETWEEN);
        layout.setSpacing("10px");
        layout.setWidth("100%");
        layout.setHeight("100%");
        add(layout);

        Div left = new Div().setWidth("100%");
        left.setMaxWidth("400px");
        left.setHeight("100%");

        authType = new ChoiceBox("Auth Type");
        authType.add("NO_AUTH", "No Auth");
        authType.add("API_KEY", "API Key");
        authType.add("BASIC", "Basic Auth");
        authType.selectIndex(0);
        authType.setWidth("100%");
        authType.setExpanse(Expanse.LARGE);
        authType.onSelect(this::onTypeChange);
        left.add(authType);

        right = new FlexLayout();
        right.setDirection(FlexDirection.COLUMN);
        right.setSpacing("10px");
        right.setWidth("100%");
        right.setHeight("100%");
        layout.add(left, right);

        placeholder = new Placeholder("This request does not use any authentication.");
        right.add(placeholder);
    }

    private void onTypeChange(ListSelectEvent event) {
        if (activeAuth != null) activeAuth.setVisible(false);

        String type = event.getSelectedItem().getKey().toString();
        switch (type) {
            case "API_KEY":
                placeholder.setVisible(false);

                if (apiKeyAuthentication == null) {
                    apiKeyAuthentication = new ApiKeyAuthentication();
                    right.add(apiKeyAuthentication);
                }

                apiKeyAuthentication.setVisible(true);
                activeAuth = apiKeyAuthentication;
                break;
            case "BASIC":
                placeholder.setVisible(false);

                if (basicAuthentication == null) {
                    basicAuthentication = new BasicAuthentication();
                    right.add(basicAuthentication);
                }

                basicAuthentication.setVisible(true);
                activeAuth = basicAuthentication;
                break;
            case "NO_AUTH":
                placeholder.setVisible(true);
                break;
        }
    }

    public HashMap<String, String> getData() {
        if (activeAuth == null) return null;
        return activeAuth.getData();
    }

}
