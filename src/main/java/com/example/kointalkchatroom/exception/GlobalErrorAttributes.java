package com.example.kointalkchatroom.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        Map<String, Object> map = super.getErrorAttributes(request, options);


        Throwable throwable = getError(request);
        map.put("status", "error");
        if (throwable instanceof GlobalException) {
            GlobalException ex = (GlobalException) throwable;
            setMessage(map, ex.getReason());
        } else {
            setMessage(map, throwable.getMessage());
        }

        map.remove("error");
        map.remove("path");
        map.remove("timestamp");
        map.remove("requestId");

        return map;
    }

    private void setMessage(Map<String, Object> map, String reason) {
        if (reason != null) {
            map.put("message", reason);
        } else {
            map.put("message", map.get("error"));
        }

    }
}
