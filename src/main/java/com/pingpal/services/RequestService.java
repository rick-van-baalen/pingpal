package com.pingpal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pingpal.models.RequestModel;

public class RequestService {
    
    public static List<RequestModel> get() {
        List<RequestModel> requests = new ArrayList<RequestModel>();

        for (int i = 1; i < 11; i++) {
            RequestModel request = new RequestModel();
            request.setId(UUID.randomUUID().toString());
            request.setName("Request " + i);
            requests.add(request);
        }

        return requests;
    }

    public static RequestModel getById(String id) {
        return null;
    }

}
