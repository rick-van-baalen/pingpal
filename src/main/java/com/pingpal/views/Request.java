package com.pingpal.views;

import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

import com.pingpal.services.RequestService;
import com.webforj.component.Composite;
import com.webforj.component.Theme;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexDirection;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.component.layout.splitter.Splitter;
import com.webforj.component.loading.Loading;

public class Request extends Composite<Div> {

    private Div self = getBoundComponent();
    private Loading loading;
    private RequestHeader header;
    private RequestToolbar toolbar;
    private RequestTabControl requestTabControl;
    private ResponseTabControl responseTabControl;

    public Request() {
        self.setWidth("100%");
        self.setHeight("100%");

        loading = new Loading("Sending request...");
        loading.getSpinner().setTheme(Theme.PRIMARY);
        self.add(loading);

        FlexLayout top = new FlexLayout();
        top.setDirection(FlexDirection.COLUMN);
        top.setSpacing("10px");
        top.setStyle("margin-bottom", "10px");

        Div bottom = new Div();
        bottom.setStyle("margin-top", "10px");

        Splitter splitter = new Splitter(top, bottom);
        splitter.setStyle("height", "100%");
        splitter.setOrientation(Splitter.Orientation.VERTICAL);
        splitter.setPositionRelative(55);
        splitter.setMasterMinSize("250px");
        splitter.setMasterMaxSize("80%");
        self.add(splitter);

        header = new RequestHeader();
        toolbar = new RequestToolbar(this);
        requestTabControl = new RequestTabControl();
        top.add(header, toolbar, requestTabControl);

        responseTabControl = new ResponseTabControl();
        bottom.add(responseTabControl);
    }

    public void sendRequest() {
        if (toolbar.getEndpoint().isBlank()) return;

        loading.open();

        try {
            RequestService service = new RequestService()
                .setMethod(toolbar.getMethod())
                .setEndpoint(toolbar.getEndpoint())
                .setAuthenticationData(requestTabControl.getAuthenticationData())
                .setParams(requestTabControl.getParams())
                .setHeaders(requestTabControl.getHeaders())
                .setBody(requestTabControl.getBody());
            
            Instant start = Instant.now();
            HttpResponse<String> response = service.send();
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            
            responseTabControl.setResponse(response, duration);
        } catch (Exception e) {
            responseTabControl.setError(e.toString());
        } finally {
            loading.close();
        }
    }
    
}
