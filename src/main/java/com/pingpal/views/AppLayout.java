package com.pingpal.views;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.router.annotation.Route;

@Route(value = "/")
public class AppLayout extends Composite<Div> {

    private Div self = getBoundComponent();

    public AppLayout() {
        self.setWidth("calc(100% - 20px)");
        self.setHeight("calc(100vh - 20px)");
        self.setStyle("padding", "10px");
        self.add(new Request());
    }
    
}
