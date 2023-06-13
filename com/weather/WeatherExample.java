package com.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WeatherExample {
    private static final String API_KEY = "403850bf40ca68508d00db1ba3507123";
    private static final String BASE_URL = "http://api.weatherstack.com/current";
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
                String[] customInfo = readCustomInfoFromFile("custom_info.txt");
                if (customInfo == null || customInfo.length < 5) {
                    System.out.println("Invalid custom information file.");
                    return;
                }

                String username = customInfo[0];
                String password = customInfo[1];
                String email = customInfo[2];
                String city = customInfo[3];
                String apiKey = customInfo[4];

                UserSystem userSystem = new UserSystem();
                userSystem.addUser(username, password);
                userSystem.setUserEmail(username, email);
                if (userSystem.authenticateUser(username, password)) {
                    System.out.println("Authentication successful for user: " + username);
                    System.out.println("Welcome, " + username + "!");
                    System.out.println();

                    CurrentWeatherData currentWeatherData = new CurrentWeatherData(API_KEY, BASE_URL);
                    System.out.println(city);
                    String currentJsonResponse = currentWeatherData.getWeatherData(city);
                    if (currentJsonResponse != null) {
                        double currentTemperature = currentWeatherData.getTemperature(currentJsonResponse);
                        double currentFeelsLikeTemperature = currentWeatherData.getFeelsLikeTemperature(currentJsonResponse);

                        System.out.println("Current Temperature in " + city + ": " + currentTemperature + "°C");
                        System.out.println("Current Feels Like Temperature in " + city + ": " + currentFeelsLikeTemperature + "°C");
                        System.out.println();
                    }

                    ForecastWeatherData forecastWeatherData = new ForecastWeatherData(API_KEY, BASE_URL);
                    String forecastJsonResponse = forecastWeatherData.getWeatherData(city);
                    if (forecastJsonResponse != null) {
                        String[] forecasts = forecastWeatherData.getWeatherForecasts(forecastJsonResponse);

                        for (String forecast : forecasts) {
                            double temperature = forecastWeatherData.getTemperature(forecast);
                            double feelsLikeTemperature = forecastWeatherData.getFeelsLikeTemperature(forecast);

                            System.out.println("Temperature in " + city + ": " + temperature + "°C");
                            System.out.println("Feels Like Temperature in " + city + ": " + feelsLikeTemperature + "°C");
                            System.out.println();
                        }
                    }
                } else {
                    System.out.println("Authentication failed for user: " + username);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }
}