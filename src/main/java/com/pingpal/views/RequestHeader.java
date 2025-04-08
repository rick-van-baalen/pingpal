package com.pingpal.views;

import com.webforj.component.Composite;
import com.webforj.component.Expanse;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexContentAlignment;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.text.Label;

public class RequestHeader extends Composite<FlexLayout> {

    private FlexLayout self = getBoundComponent();

    public RequestHeader() {
        self.setWidth("100%");
        self.setAlignment(FlexAlignment.CENTER);
        self.setJustifyContent(FlexJustifyContent.BETWEEN);

        Div left = new Div().setWidth("100%");
        Label label = new Label("Folder / Request 1");
        left.add(label);

        FlexLayout right = new FlexLayout().setWidth("100%");
        right.setAlignment(FlexAlignment.CENTER);
        right.setJustifyContent(FlexJustifyContent.END);
        right.setSpacing("10px");

        self.add(left, right);
    }
    
}
