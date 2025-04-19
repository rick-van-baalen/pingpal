package com.pingpal.services;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pingpal.helpers.RequestHandler;
import com.pingpal.models.RequestModel;
import com.webforj.component.optiondialog.OptionDialog;

public class RequestService {
    
    public static List<RequestModel> get() {
        try {
            RequestHandler service = new RequestHandler()
                .setMethod("GET")
                .setEndpoint("http://localhost:8080/requests")
                .setConsoleLogging(false);

            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            service.setHeaders(headers);

            HttpResponse<String> response = service.send();

            Type listType = new TypeToken<List<RequestModel>>() {}.getType();
            return new Gson().fromJson(response.body(), listType);
        } catch (Exception e) {
            OptionDialog.showMessageDialog(e.toString());
        }

        return null;
    }

    public static RequestModel add(RequestModel model) {
        try {
            RequestHandler service = new RequestHandler()
                .setMethod("POST")
                .setEndpoint("http://localhost:8080/requests")
                .setConsoleLogging(false);

            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            service.setHeaders(headers);

            String body = new Gson().toJson(model);
            service.setBody(body);

            HttpResponse<String> response = service.send();
            return new Gson().fromJson(response.body(), RequestModel.class);
        } catch (Exception e) {
            OptionDialog.showMessageDialog(e.toString());
        }

        return null;
    }
    
    public static RequestModel update(RequestModel model) {
        try {
            RequestHandler service = new RequestHandler()
                .setMethod("PUT")
                .setEndpoint("http://localhost:8080/requests/" + model.getId())
                .setConsoleLogging(false);

            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            service.setHeaders(headers);

            String body = new Gson().toJson(model);
            service.setBody(body);

            HttpResponse<String> response = service.send();
            return new Gson().fromJson(response.body(), RequestModel.class);
        } catch (Exception e) {
            OptionDialog.showMessageDialog(e.toString());
        }

        return null;
    }

    public static RequestModel delete(String id) {
        try {
            RequestHandler service = new RequestHandler()
                .setMethod("DELETE")
                .setEndpoint("http://localhost:8080/requests/" + id)
                .setConsoleLogging(false);

            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            service.setHeaders(headers);

            service.send();
        } catch (Exception e) {
            OptionDialog.showMessageDialog(e.toString());
        }

        return null;
    }

    public static RequestModel getById(String id) {
        try {
            RequestHandler service = new RequestHandler()
                .setMethod("GET")
                .setEndpoint("http://localhost:8080/requests/" + id)
                .setConsoleLogging(false);

            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json");
            service.setHeaders(headers);

            HttpResponse<String> response = service.send();
            RequestModel model = new Gson().fromJson(response.body(), RequestModel.class);
            
            return model;
        } catch (Exception e) {
            OptionDialog.showMessageDialog(e.toString());
        }

        return null;
    }

}
