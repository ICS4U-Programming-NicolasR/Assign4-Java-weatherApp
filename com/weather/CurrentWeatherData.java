package com.weather;

// child class of weatherdata
class CurrentWeatherData extends WeatherData {
  public CurrentWeatherData(String apiKey, String baseUrl) {
    // super references the fields found in the parent class
    super(apiKey, baseUrl);
  }

  // get the temperature
  public double getTemperature(String jsonResponse) {
    // checks for where in the string "temperature" is
    int startIndex = jsonResponse.indexOf("\"temperature\":");
    // sets that as the start index
    if (startIndex != -1) {
      // finds where the string ends
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        // creates a new string by taking a substring of the whole output
        String temperatureString = jsonResponse.substring(startIndex + 14, endIndex);
        // returns it back to the main code
        return Double.parseDouble(temperatureString);
      }
    }
    // return NaN if nothing
    return Double.NaN;
  }

  // the same method as above except for wind speed
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

  // the same method as above except for precipitation
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

  // the same method as above except for humidity
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

  // the same method as above except for pressure
  public double getPressure(String jsonResponse) {
    int startIndex = jsonResponse.indexOf("\"pressure\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String pressure = jsonResponse.substring(startIndex + 11, endIndex);
        return Double.parseDouble(pressure);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for cloud cover
  public double getCloudCover(String jsonResponse) {
    int startIndex = jsonResponse.indexOf("\"cloudcover\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String cloudCover = jsonResponse.substring(startIndex + 13, endIndex);
        return Double.parseDouble(cloudCover);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for uv level
  public String getUvLevel(String jsonResponse) {
    int startIndex = jsonResponse.indexOf("\"uv_index\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String uvLevelStr = jsonResponse.substring(startIndex + 11, endIndex);
        int uvLevel = Integer.parseInt(uvLevelStr);
        // it does check if it is high low or moderate and returns that instead
        if (uvLevel > 5) {
          return "high! Put on sunscreen!";
        } else if (uvLevel > 2) {
          return "moderate: Sunscreen is recommended";
        } else {
          return "low: Sunscreen is uneeded";
        }
      }
    }
    return "";
  }

  // the same method as above except for visibility
  public double getVisibility(String jsonResponse) {
    int startIndex = jsonResponse.indexOf("\"visibility\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String visibility = jsonResponse.substring(startIndex + 13, endIndex);
        return Double.parseDouble(visibility);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for wind dir
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
    // checks the degrees and returns the output in words rather than numbers
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

  // the same method as above except for location(needs three things)
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

  // the same method as above except for time
  public String getTime(String jsonResponse) {
    int startIndex = jsonResponse.indexOf("\"localtime\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String time = jsonResponse.substring(startIndex + 13, endIndex - 1);
        return time;
      }
    }
    return "";
  }

  // the same method as above except for weather description
  public String getDescription(String jsonResponse) {
    int startIndex = jsonResponse.indexOf("\"weather_descriptions\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String description = jsonResponse.substring(startIndex + 25, endIndex - 2);
        return description;
      }
    }
    return "";
  }

  // the same method as above except for feels like temperature
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
