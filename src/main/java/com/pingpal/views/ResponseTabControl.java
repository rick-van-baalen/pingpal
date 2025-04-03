package com.pingpal.views;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import com.pingpal.components.TabControl;

public class ResponseTabControl extends TabControl {

    private ResponseBody responseBody;
    private ResponseHeaders responseHeaders;
    private ResponseStatusCode responseCode;
    private ResponseDuration responseDuration;
    private ResponseBytes responseBytes;
    private Console console;

    public ResponseTabControl() {
        responseBody = new ResponseBody();
        addTab("Body", responseBody);

        responseHeaders = new ResponseHeaders();
        addTab("Headers", responseHeaders);

        console = new Console();
        addTab("Console", console);

        responseCode = new ResponseStatusCode();
        responseDuration = new ResponseDuration();
        responseBytes = new ResponseBytes();
        addExtraContent(responseCode, responseDuration, responseBytes);
    }

    public void setResponse(HttpResponse<String> response, Duration duration) {
        responseCode.setData(response.statusCode());
        responseDuration.setData(duration);

        byte[] bodyBytes = response.body().getBytes(StandardCharsets.UTF_8);
        int byteLength = bodyBytes.length;
        responseBytes.setData(byteLength);
        
        responseBody.setData(response.body());
        responseHeaders.setData(response.headers());
    }

    public void setError(String errorMessage) {
        responseCode.setError();
        responseBody.setData(errorMessage);
    }
    
}
