package com.weather;

class CurrentWeatherData extends WeatherData {
    public CurrentWeatherData(String apiKey, String baseUrl) {
        super(apiKey, baseUrl);
    }

    public double getTemperature(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"temperature\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String temperatureString = jsonResponse.substring(startIndex + 14, endIndex);
                return Double.parseDouble(temperatureString);
            }
        }
        return Double.NaN;
    }

    public double getWindSpeed(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"wind_speed\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String windSpeed = jsonResponse.substring(startIndex + 13, endIndex);
                return Double.parseDouble(windSpeed);
            }
        }
        return Double.NaN;
    }

    public double getPecipitation(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"precip\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String precip = jsonResponse.substring(startIndex + 9, endIndex);
                return Double.parseDouble(precip);
            }
        }
        return Double.NaN;
    }

    public double getHumidity(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"humidity\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String humidity = jsonResponse.substring(startIndex + 11, endIndex);
                return Double.parseDouble(humidity);
            }
        }
        return Double.NaN;
    }

    public String getWindDir(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"wind_degree\":");
        int degrees = 0;
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String windDir = jsonResponse.substring(startIndex + 14, endIndex);
                degrees = Integer.parseInt(windDir);
            }
        }
        if (degrees >= 337.5 || degrees < 22.5) {
            return "Northern";
        } else if (degrees >= 22.5 && degrees < 67.5) {
            return "Northeastern";
        } else if (degrees >= 67.5 && degrees < 112.5) {
            return "Eastern";
        } else if (degrees >= 112.5 && degrees < 157.5) {
            return "Southeastern";
        } else if (degrees >= 157.5 && degrees < 202.5) {
            return "Southern";
        } else if (degrees >= 202.5 && degrees < 247.5) {
            return "Southwestern";
        } else if (degrees >= 247.5 && degrees < 292.5) {
            return "Western";
        } else {
            return "Northwestern";
        }
    }


    public String getLocation(String jsonResponse) {
        String city = "";
        String country = "";
        String region = "";
        int startIndex = jsonResponse.indexOf("\"name\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                city = jsonResponse.substring(startIndex + 8, endIndex - 1);
            }
        }
        startIndex = jsonResponse.indexOf("\"country\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                country = jsonResponse.substring(startIndex + 11, endIndex - 1);
            }
        }

        startIndex = jsonResponse.indexOf("\"region\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                region = jsonResponse.substring(startIndex + 10, endIndex - 1);
            }
        }
        return city + ", " + region + ", " + country;
    }

    public String getTime(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"observation_time\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String time = jsonResponse.substring(startIndex + 20, endIndex - 1);
                return time;
            }
        }
        return "";
    }

    public String getDescription(String jsonResponse) {
        int startIndex = jsonResponse.indexOf("\"weather_descriptions\":");
        if (startIndex != -1) {
            int endIndex = jsonResponse.indexOf(",", startIndex);
            if (endIndex != -1) {
                String time = jsonResponse.substring(startIndex + 25, endIndex - 2);
                return time;
            }
        }
        return "";
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