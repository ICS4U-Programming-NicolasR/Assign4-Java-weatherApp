package com.weather;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// parent class to currentweatherdata
public class WeatherData {
  protected String apiKey;
  protected String baseUrl;

  public WeatherData(String apiKey, String baseUrl) {
    this.apiKey = apiKey;
    this.baseUrl = baseUrl;
  }

  // gets the weather data from the base url using the access key
  protected String getWeatherData(String city) throws IOException {
    String urlString = baseUrl + "?access_key=" + apiKey + "&query=" + city;
    URL url = new URL(urlString);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");

    int responseCode = connection.getResponseCode();
    // if the http code is 200 it means that the connection works
    if (responseCode == 200) {
      Scanner scanner = new Scanner(connection.getInputStream());
      // build the string from the data on the website.
      StringBuilder response = new StringBuilder();
      while (scanner.hasNextLine()) {
        response.append(scanner.nextLine());
      }
      scanner.close();
      return response.toString();
    }

    return null;
  }
}
