package com.pingpal.models;

public class RequestModel {
    
    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static RequestModel create(String name) {
        RequestModel request = new RequestModel();
        request.setName(name);
        return request;
    }

}
