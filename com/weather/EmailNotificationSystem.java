package com.weather;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class EmailNotificationSystem {
    private final String apiKey;

    public EmailNotificationSystem(String apiKey) {
        this.apiKey = apiKey;
    }

    public void sendEmail(String recipient, String subject, String body) throws IOException {
        URL url = new URL("https://api.elasticemail.com/v2/email/send");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("apikey", apiKey);
        parameters.put("from", "nicolas.riscalas@stu.ocsb.ca");
        parameters.put("fromName", "Nicolas Riscalas");
        parameters.put("subject", subject);
        parameters.put("bodyHtml", body);
        parameters.put("to", recipient);
        String requestBody = buildQueryString(parameters);

        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
            outputStream.write(requestBodyBytes, 0, requestBodyBytes.length);
        }

        int responseCode = connection.getResponseCode();
        System.out.println("Email sent. Response code: " + responseCode);
    }

    private String buildQueryString(Map<String, String> parameters) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            queryString.append(key).append('=').append(value).append('&');
        }
        queryString.deleteCharAt(queryString.length() - 1); // Remove trailing '&'
        return queryString.toString();
    }
}
