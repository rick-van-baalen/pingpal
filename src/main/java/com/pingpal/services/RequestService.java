package com.pingpal.services;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import com.pingpal.helpers.BytesFormatter;
import com.pingpal.helpers.DurationFormatter;
import com.pingpal.helpers.StatusCodeFormatter;
import com.pingpal.views.Console;
import com.webforj.environment.ObjectTable;

import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;

public class RequestService {
    
    private HttpClient client = HttpClient.newHttpClient();
    private String method = "GET", endpoint, body;
    private HashMap<String, String> authentication, params, headers;

    public HttpResponse<String> send() throws Exception {
        Builder requestBuilder = HttpRequest.newBuilder();

        String fullUrl = endpoint;
        if (method.equalsIgnoreCase("GET") && !params.isEmpty()) fullUrl += "?" + getParamString(params);
        requestBuilder.uri(new URI(fullUrl));
        
        for (Map.Entry<String, String> header : headers.entrySet()) {
            requestBuilder.header(header.getKey(), header.getValue());
        }

        if (authentication != null && authentication.containsKey("AUTH_TYPE")) {
            String type = authentication.get("AUTH_TYPE");
            switch (type) {
                case "API_KEY":
                    String key = authentication.get("KEY");
                    String value = authentication.get("VALUE");
                    requestBuilder.header(key, value);
                    break;
            }
        }

        if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
            requestBuilder.method(method, BodyPublishers.ofString(body));
        } else {
            requestBuilder.method(method, BodyPublishers.noBody());
        }

        HttpRequest request = requestBuilder.build();

        Instant start = Instant.now();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        String prettyTime = DurationFormatter.format(duration);

        String prettyStatus = StatusCodeFormatter.format(response.statusCode());

        byte[] bodyBytes = response.body().getBytes(StandardCharsets.UTF_8);
        int byteLength = bodyBytes.length;
        String prettyBytes = BytesFormatter.format(byteLength);

        Console console = (Console) ObjectTable.get("CONSOLE");
        console.print(method + " " + fullUrl + " | " + prettyStatus + " | " + prettyTime + " | " + prettyBytes);
        
        return response;
    }

    private String getParamString(HashMap<String, String> params) {
        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            joiner.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return joiner.toString();
    }

    public RequestService setMethod(String method) {
        this.method = method;
        return this;
    }

    public RequestService setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public RequestService setAuthenticationData(HashMap<String, String> data) {
        this.authentication = data;
        return this;
    }

    public RequestService setParams(HashMap<String, String> params) {
        this.params = params;
        return this;
    }

    public RequestService setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RequestService setBody(String body) {
        this.body = body;
        return this;
    }

}
