package com.pingpal.services;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.pingpal.helpers.RequestHandler;
import com.pingpal.models.RequestModel;
import com.webforj.component.optiondialog.OptionDialog;

public class RequestService {
    
    public static List<RequestModel> get() {
        List<RequestModel> requests = new ArrayList<RequestModel>();

        try {
            RequestHandler service = new RequestHandler()
                .setMethod("GET")
                .setEndpoint("http://localhost:8080/requests");

            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            service.setHeaders(headers);

            HttpResponse<String> response = service.send();
            OptionDialog.showMessageDialog(response.body());
        } catch (Exception e) {
            OptionDialog.showMessageDialog(e.toString());
        }

        return requests;
    }

    public static RequestModel add(RequestModel model) {
        try {
            RequestHandler service = new RequestHandler()
                .setMethod("POST")
                .setEndpoint("http://localhost:8080/requests");

            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            service.setHeaders(headers);

            String body = new Gson().toJson(model);
            service.setBody(body);

            HttpResponse<String> response = service.send();
            OptionDialog.showMessageDialog(response.body());
        } catch (Exception e) {
            OptionDialog.showMessageDialog(e.toString());
        }

        return model;
    }

    public static RequestModel getById(String id) {
        return null;
    }

}
