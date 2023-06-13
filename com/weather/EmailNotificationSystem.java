package com.weather;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

public class EmailNotificationSystem {
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public EmailNotificationSystem(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void sendEmailNotification(String recipient, String subject, String message) {
        try (Socket socket = new Socket(host, port);
             OutputStream outputStream = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(outputStream, true)) {

            String encodedUsername = Base64.getEncoder().encodeToString(username.getBytes());
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());

            // Send initial connection commands
            writer.printf("EHLO %s\r\n", host);
            writer.printf("AUTH LOGIN\r\n");
            writer.printf("%s\r\n", encodedUsername);
            writer.printf("%s\r\n", encodedPassword);

            // Send email data
            writer.printf("MAIL FROM:<%s>\r\n", username);
            writer.printf("RCPT TO:<%s>\r\n", recipient);
            writer.printf("DATA\r\n");
            writer.printf("Subject:%s\r\n", subject);
            writer.printf("\r\n");
            writer.printf("%s\r\n", message);
            writer.printf(".\r\n");

            // Close the connection
            writer.printf("QUIT\r\n");

            System.out.println("Email notification sent successfully!");
        } catch (Exception e) {
            System.out.println("Failed to send email notification: " + e.getMessage());
        }
    }
}
