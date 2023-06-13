package com.weather;

class CurrentWeatherData extends WeatherData {
    public CurrentWeatherData(String apiKey, String baseUrl) {
        super(apiKey, baseUrl);
    }

    public double getTemperature(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"temperature\":");
        System.out.println(startIndex);
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String temperatureString = jsonResponse.substring(startIndex + 14, endIndex);
                return Double.parseDouble(temperatureString);
            }
        }
        return Double.NaN;
    }

    public double getFeelsLikeTemperature(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"feelslike\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String feelsLikeString = jsonResponse.substring(startIndex + 12, endIndex);
                return Double.parseDouble(feelsLikeString);
            }
        }
        return Double.NaN;
    }
}