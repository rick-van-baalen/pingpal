package com.pingpal.views.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.H3;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.icons.Icon;
import com.webforj.component.icons.IconButton;
import com.webforj.component.icons.TablerIcon;
import com.webforj.component.layout.flexlayout.FlexAlignment;
import com.webforj.component.layout.flexlayout.FlexContentAlignment;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexJustifyContent;
import com.webforj.component.layout.flexlayout.FlexLayout;

public class RequestsManager extends Composite<FlexLayout> {

    private FlexLayout self = getBoundComponent();
    private FlexLayout requestsContainer;
    private HashMap<String, FlexLayout> requests = new HashMap<String, FlexLayout>();
    private FlexLayout selectedRequest;

    public RequestsManager() {
        self.setDirection(FlexDirection.COLUMN);
        self.setMaxWidth("400px");
        self.setWidth("100%");
        self.setHeight("100%");
        self.setAlignment(FlexAlignment.START);
        self.setPadding("10px");
        self.setStyle("background-color", "#F7F7F7");
        self.setStyle("border-radius", "5px");

        H3 heading = new H3("Requests");
        heading.setStyle("margin-bottom", "10px");
        self.add(heading);

        Icon icon = TablerIcon.create("plus");
        IconButton button = new IconButton(icon);
        button.onClick(e -> {
            addRequest("New request");
        });
        button.setStyle("margin-bottom", "10px");
        self.add(button);

        requestsContainer = new FlexLayout();
        requestsContainer.setWidth("100%");
        requestsContainer.setHeight("100%");
        requestsContainer.setDirection(FlexDirection.COLUMN);
        requestsContainer.setSpacing("10px");
        self.add(requestsContainer);

        for (int i = 0; i < 10; i++) {
            addRequest("Request " + (i + 1));
        }
    }

    private void addRequest(String name) {
        String uuid = UUID.randomUUID().toString();

        FlexLayout requestContainer = new FlexLayout().addClassName("request-container");
        requestContainer.setUserData("UUID", uuid);
        requestContainer.setJustifyContent(FlexJustifyContent.BETWEEN);
        requestContainer.setAlignContent(FlexContentAlignment.CENTER);
        requestContainer.setSpacing("10px");
        requestContainer.setPadding("10px");
        requestContainer.setStyle("border", "1px solid #E7E7E7");
        requestContainer.setStyle("cursor", "pointer");
        requestContainer.onClick(e -> {
            openRequest(requestContainer);
        });
        requestsContainer.add(requestContainer);
        requests.put(uuid, requestContainer);

        Paragraph p = new Paragraph(name);

        Icon icon = TablerIcon.create("trash");
        IconButton button = new IconButton(icon);
        button.onClick(e -> {
            requests.remove(uuid);
            requestContainer.destroy();
        });

        requestContainer.add(p, button);

        openRequest(requestContainer);
    }

    private void openRequest(FlexLayout activeRequest) {
        selectedRequest = activeRequest;
        
        Iterator<FlexLayout> it = requests.values().iterator();
        while (it.hasNext()) {
            FlexLayout request = it.next();
            request.removeClassName("active");
        }

        selectedRequest.addClassName("active");
    }
    
}
