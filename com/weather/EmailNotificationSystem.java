package com.weather;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * EmailNotificationSystem.java.
 *
 * @author Mr. Riscalas
 * @version 1.0
 * @since 2023-06-15
 */
public class EmailNotificationSystem {
  /**
   * This is the send email method
   *
   * @param recipient // recipient
   * @param subject // subject
   * @param body // body
   */
  private final String apiKey;

  // get the api key
  public EmailNotificationSystem(final String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * This is the send email method
   *
   * @param recipient // recipient
   * @param subject // subject
   * @param body // body
   */
  public void sendEmail(String recipient, String subject, String body) throws IOException {
    // create a url and get a connection to the server
    URL url = new URL("https://api.elasticemail.com/v2/email/send");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    connection.setDoOutput(true);
    // create a hashmap that has all the needed info for sending an email
    Map<String, String> parameters = new HashMap<>();
    parameters.put("apikey", apiKey);
    parameters.put("from", "nicolas.riscalas@stu.ocsb.ca");
    parameters.put("fromName", "Nicolas Riscalas");
    parameters.put("subject", subject);
    parameters.put("bodyHtml", body);
    parameters.put("to", recipient);
    String requestBody = buildQueryString(parameters);

    // try to get a connection to send the body
    try (OutputStream outputStream = connection.getOutputStream()) {
      byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
      outputStream.write(requestBodyBytes, 0, requestBodyBytes.length);
    }

    // get the connection responseCode if 200 connection ok
    int responseCode = connection.getResponseCode();
    System.out.println("Email sent. Response code: " + responseCode);
  }

  // takes the paramaters and creates it into the needed info for the email.
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
