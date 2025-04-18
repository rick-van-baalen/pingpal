package com.pingpal.views.layout;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.router.annotation.Route;

@Route(value = "/")
public class AppLayout extends Composite<Div> {

    private Div self = getBoundComponent();
    private RequestsManager requestsManager;

    public AppLayout() {
        self.setWidth("100%");
        self.setHeight("100vh");
        self.setStyle("display", "flex");
        self.setStyle("justify-content", "space-between");
        self.setStyle("gap", "10px");
        
        requestsManager = new RequestsManager();
        self.add(requestsManager);
    }
    
}
