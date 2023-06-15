package com.weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class WeatherExample {
    private static final String WEATHER_API_KEY = "403850bf40ca68508d00db1ba3507123";
    private static final String EMAIL_API_KEY = "45B55A71ECD2C835E3A7CFFD1B122A17ABFC8902E1576DFF0E6F9C9B173A1031590AB51C19D471768F4C74EC596AB0BE";
  private static final String BASE_URL = "http://api.weatherstack.com/";

  private static String[] readCustomInfoFromFile(String filePath) {
    try {
      File file = new File(filePath);
      Scanner scanner = new Scanner(file);

      String[] customInfo = new String[5];
      int index = 0;
      while (scanner.hasNextLine()) {
        customInfo[index] = scanner.nextLine();
        index++;
      }

      scanner.close();
      return customInfo;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    // Read custom information from file
    String[] customInfo = readCustomInfoFromFile("input.txt");
    if (customInfo == null || customInfo.length < 5) {
      System.out.println("Invalid custom information file.");
      return;
    }

    String username = customInfo[0];
    String password = customInfo[1];
    String email = customInfo[2];
    System.out.println(email);
    String city = customInfo[3];
    String apiKey = customInfo[4];

    UserSystem userSystem = new UserSystem();
    userSystem.addUser(username, password);
    userSystem.setUserEmail(username, email);
    if (userSystem.authenticateUser(username, password)) {
      System.out.println("Authentication successful for user: " + username);
      System.out.println("Welcome, " + username + "!");
      System.out.println();

      CurrentWeatherData currentWeatherData = new CurrentWeatherData(WEATHER_API_KEY, BASE_URL + "current");
      try {
        String currentJsonResponse = currentWeatherData.getWeatherData(city);
        if (currentJsonResponse != null) {
          double currentTemperature = currentWeatherData.getTemperature(currentJsonResponse);
          double currentFeelsLikeTemperature =
              currentWeatherData.getFeelsLikeTemperature(currentJsonResponse);
          String location = currentWeatherData.getLocation(currentJsonResponse);
          String time = currentWeatherData.getTime(currentJsonResponse);
          String weather = currentWeatherData.getDescription(currentJsonResponse);
          double windSpeed = currentWeatherData.getWindSpeed(currentJsonResponse);
          String windDir = currentWeatherData.getWindDir(currentJsonResponse);
          double precip = currentWeatherData.getPecipitation(currentJsonResponse);
          double humidity = currentWeatherData.getHumidity(currentJsonResponse);
          System.out.println(currentJsonResponse);
          System.out.println(
              "The weather in "
                  + location
                  + " At "
                  + time
                  + " is currently: \n"
                  + weather
                  + " with a temperature of "
                  + currentTemperature
                  + "\u00B0C and a feels like temperature of "
                  + currentFeelsLikeTemperature
                  + "\u00B0C. \nThere is a "
                  + windDir
                  + " wind going through at "
                  + windSpeed
                  + "km/h\nThe amount of precipitation predicted is "
                  + precip
                  + "mm with a humidity of "
                  + humidity);
          System.out.println();
           // Send email notification
                EmailNotificationSystem emailSystem = new EmailNotificationSystem(EMAIL_API_KEY);
                String subject = "Weather Update: " + city;
                String body = "Current Temperature: " + currentTemperature + "°C\n" +
                        "Feels Like Temperature: " + currentFeelsLikeTemperature + "°C";
                // emailSystem.sendEmail(email, subject, body);
                System.out.println("Email notification sent to: " + email);
        }
      } catch (IOException e) {
        System.out.println("An error occurred while retrieving weather data: " + e.getMessage());
      }
    } else {
      System.out.println("Authentication failed for user: " + username);
    }
  }
}
