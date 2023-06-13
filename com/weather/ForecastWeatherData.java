package com.weather;

class ForecastWeatherData extends WeatherData {
    public ForecastWeatherData(String apiKey, String baseUrl) {
        super(apiKey, baseUrl);
    }

    public String[] getWeatherForecasts(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"forecast\":[");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf("]", startIndex);
            if (endIndex != -1) {
                String forecastString = jsonResponse.substring(startIndex + 12, endIndex);
                return forecastString.split("\\},\\{");
            }
        }
        return new String[0];
    }

    public double getTemperature(String forecast) {
        int startIndex = forecast.indexOf("\"temperature\":");
        if (startIndex != -1) {
            int endIndex = forecast.indexOf(",", startIndex);
            if (endIndex != -1) {
                String temperatureString = forecast.substring(startIndex + 15, endIndex);
                return Double.parseDouble(temperatureString);
            }
        }
        return Double.NaN;
    }

    public double getFeelsLikeTemperature(String forecast) {
        int startIndex = forecast.indexOf("\"feelslike\":");
        if (startIndex != -1) {
            int endIndex = forecast.indexOf(",", startIndex);
            if (endIndex != -1) {
                String feelsLikeString = forecast.substring(startIndex + 13, endIndex);
                return Double.parseDouble(feelsLikeString);
            }
        }
        return Double.NaN;
    }
}
