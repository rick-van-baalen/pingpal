package com.pingpal.views;

import java.util.HashMap;

import com.pingpal.components.KeyValue;
import com.pingpal.components.TabControl;

public class RequestTabControl extends TabControl {

    private KeyValue params;
    private RequestAuthentication authentication;
    private KeyValue headers;
    private RequestBody body;

    public RequestTabControl() {
        params = new KeyValue();
        addTab("Params", params);

        authentication = new RequestAuthentication();
        addTab("Authentication", authentication);

        headers = new KeyValue();
        addTab("Headers", headers);

        body = new RequestBody();
        addTab("Body", body);
    }

    public HashMap<String, String> getAuthenticationData() {
        return authentication.getData();
    }

    public HashMap<String, String> getParams() {
        return params.getData();
    }

    public HashMap<String, String> getHeaders() {
        return headers.getData();
    }

    public String getBody() {
        return body.getData();
    }
    
}
