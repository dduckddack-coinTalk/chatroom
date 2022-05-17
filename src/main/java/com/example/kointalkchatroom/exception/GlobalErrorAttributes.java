package com.example.kointalkchatroom.exception;

import com.example.kointalkchatroom.kafka.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private final Producer producer;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        Map<String, Object> map = super.getErrorAttributes(request, options);
        Throwable throwable = getError(request);

        Integer status = (Integer) map.get("status");
        if(status != 404) {
            String messages = getExceptionMessages(throwable);
            producer.sendMessage(messages);
        }
        if (throwable instanceof GlobalException) {
            GlobalException ex = (GlobalException) throwable;
            makeMessage(map, ex.getReason());
        } else {
            makeMessage(map, throwable.getMessage());
        }



        return map;
    }

    private String getExceptionMessages(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);

        String[] messages = sw.toString().split("Original Stack Trace:");
        String message = messages[0];

        String prefix = sdf.format(new Date());
        return String.format("---------------------------\r\n%s\r\n%s", prefix, message);
    }

    private void makeMessage(Map<String, Object> map, String reason) {
        map.put("status", "error");
        if (reason != null) {
            map.put("message", reason);
        } else {
            map.put("message", map.get("error"));
        }
        map.remove("error");
        map.remove("path");
        map.remove("timestamp");
        map.remove("requestId");

    }
}
