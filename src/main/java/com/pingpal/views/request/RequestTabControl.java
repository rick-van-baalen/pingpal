package com.pingpal.views.request;

import java.util.HashMap;

import com.pingpal.components.JsonEditor;
import com.pingpal.components.KeyValue;
import com.pingpal.components.TabControl;
import com.webforj.component.optiondialog.OptionDialog;

public class RequestTabControl extends TabControl {

    private KeyValue params;
    private RequestAuthentication authentication;
    private KeyValue headers;
    private JsonEditor body;

    public RequestTabControl() {
        params = new KeyValue();
        addTab("Params", params);

        authentication = new RequestAuthentication();
        addTab("Authentication", authentication);

        headers = new KeyValue();
        addTab("Headers", headers);

        body = new JsonEditor();
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
