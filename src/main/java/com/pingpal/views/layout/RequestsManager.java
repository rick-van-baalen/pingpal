package com.pingpal.views.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.pingpal.models.RequestModel;
import com.pingpal.services.RequestService;
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
import com.webforj.router.Router;
import com.webforj.router.history.Location;

public class RequestsManager extends Composite<FlexLayout> {

    private FlexLayout self = getBoundComponent();
    private FlexLayout requestsContainer;
    private HashMap<String, FlexLayout> requests = new HashMap<String, FlexLayout>();
    private FlexLayout selectedRequest;

    public RequestsManager() {
        self.addClassName("requests-manager");
        self.setDirection(FlexDirection.COLUMN);
        self.setMaxWidth("400px");
        self.setWidth("100%");
        self.setHeight("100%");
        self.setAlignment(FlexAlignment.START);
        self.setPadding("10px");

        H3 heading = new H3("Requests").addClassName("requests-manager-heading");
        self.add(heading);

        Icon icon = TablerIcon.create("plus");
        IconButton button = new IconButton(icon);
        button.addClassName("requests-manager-new-button");
        button.onClick(e -> {
            RequestModel model = RequestModel.create("New request");
            model = RequestService.add(model);
            buildRequest(model);
        });
        self.add(button);

        requestsContainer = new FlexLayout();
        requestsContainer.setWidth("100%");
        requestsContainer.setHeight("100%");
        requestsContainer.setDirection(FlexDirection.COLUMN);
        requestsContainer.setSpacing("10px");
        self.add(requestsContainer);

        List<RequestModel> requests = RequestService.get();
        for (RequestModel request : requests) {
            buildRequest(request);
        }
    }

    private void buildRequest(RequestModel request) {
        FlexLayout requestContainer = new FlexLayout().addClassName("request-container");
        requestContainer.setUserData("UUID", request.getId());
        requestContainer.setJustifyContent(FlexJustifyContent.BETWEEN);
        requestContainer.setAlignContent(FlexContentAlignment.CENTER);
        requestContainer.setSpacing("10px");
        requestContainer.setPadding("10px");
        requestContainer.onClick(e -> {
            setActive(requestContainer);
            Router.getCurrent().navigate(new Location("/requests/" + request.getId()));
        });
        
        requestsContainer.add(requestContainer);
        requests.put(request.getId(), requestContainer);

        Paragraph p = new Paragraph(request.getName());

        Icon icon = TablerIcon.create("trash");
        IconButton button = new IconButton(icon);
        button.onClick(e -> {
            requests.remove(request.getId());
            requestContainer.destroy();
            RequestService.delete(request.getId());
        });

        requestContainer.add(p, button);
    }

    private void setActive(FlexLayout activeRequest) {
        selectedRequest = activeRequest;
        
        Iterator<FlexLayout> it = requests.values().iterator();
        while (it.hasNext()) {
            FlexLayout request = it.next();
            request.removeClassName("active");
        }

        selectedRequest.addClassName("active");
    }
    
}
