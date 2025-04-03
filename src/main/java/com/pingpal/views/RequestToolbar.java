package com.pingpal.views;

import com.webforj.component.Composite;
import com.webforj.component.Expanse;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.field.TextField;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.list.ChoiceBox;

public class RequestToolbar extends Composite<FlexLayout> {

    private FlexLayout self = getBoundComponent();
    private ChoiceBox methods;
    private TextField endpoint;

    public RequestToolbar(Request request) {
        self.setWidth("100%");
        self.setAlignment(FlexAlignment.CENTER);
        self.setJustifyContent(FlexJustifyContent.BETWEEN);

        methods = new ChoiceBox();
        methods.add("GET", "GET");
        methods.add("POST", "POST");
        methods.add("PUT", "PUT");
        methods.add("DELETE", "DELETE");
        methods.selectIndex(0);
        methods.setWidth("100%");
        methods.setMaxWidth("150px");
        methods.setExpanse(Expanse.LARGE);

        endpoint = new TextField();
        endpoint.setWidth("100%");
        endpoint.setExpanse(Expanse.LARGE);
        endpoint.setText("http://cst24.prodin.software:8888/26a63798-4c29-48eb-bca3-59601a9ae863/lsmint/rest/HelloWorld");

        Button send = new Button("Send");
        send.setExpanse(Expanse.LARGE);
        send.setTheme(ButtonTheme.PRIMARY);
        send.onClick(e -> request.sendRequest());

        self.add(methods, endpoint, send);
    }

    public String getMethod() {
        return methods.getSelectedKey().toString();
    }
    
    public String getEndpoint() {
        return endpoint.getText().trim();
    }
    
}
