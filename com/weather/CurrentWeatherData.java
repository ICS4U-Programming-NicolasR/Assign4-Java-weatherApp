package com.weather;

// child class of weatherdata
class CurrentWeatherData extends WeatherData {
  protected CurrentWeatherData(final String apiKey, final String baseUrl) {
    // super references the fields found in the parent class
    super(apiKey, baseUrl);
  }

  // get the temperature
  public double getTemperature(final String jsonResponse) {
    final int length = 14;
    // checks for where in the string "temperature" is
    int startIndex = jsonResponse.indexOf("\"temperature\":");
    // sets that as the start index
    if (startIndex != -1) {
      // finds where the string ends
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        // creates a new string by taking a substring of the whole output
        String temperatureString = jsonResponse.substring(
          startIndex + length, endIndex);
        // returns it back to the main code
        return Double.parseDouble(temperatureString);
      }
    }
    // return NaN if nothing
    return Double.NaN;
  }

  // the same method as above except for wind speed
  public double getWindSpeed(final String jsonResponse) {
    final int length = 13;
    int startIndex = jsonResponse.indexOf("\"wind_speed\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String windSpeed = jsonResponse.substring(
        startIndex + length, endIndex);
        return Double.parseDouble(windSpeed);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for precipitation
  public double getPecipitation(final String jsonResponse) {
    final int length = 9;
    int startIndex = jsonResponse.indexOf("\"precip\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String precip = jsonResponse.substring(startIndex + length, endIndex);
        return Double.parseDouble(precip);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for humidity
  public double getHumidity(final String jsonResponse) {
    final int length = 11;
    int startIndex = jsonResponse.indexOf("\"humidity\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String humidity = jsonResponse.substring(startIndex + length, endIndex);
        return Double.parseDouble(humidity);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for pressure
  public double getPressure(final String jsonResponse) {
    final int length = 11;
    int startIndex = jsonResponse.indexOf("\"pressure\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String pressure = jsonResponse.substring(startIndex + length, endIndex);
        return Double.parseDouble(pressure);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for cloud cover
  public double getCloudCover(final String jsonResponse) {
    final int length = 13;
    int startIndex = jsonResponse.indexOf("\"cloudcover\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String cloudCover = jsonResponse.substring(
        startIndex + length, endIndex);
        return Double.parseDouble(cloudCover);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for uv level
  public String getUvLevel(final String jsonResponse) {
    final int length = 11;
    int startIndex = jsonResponse.indexOf("\"uv_index\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String uvLevelStr = jsonResponse.substring(
        startIndex + length, endIndex);
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
  public double getVisibility(final String jsonResponse) {
    final int length = 13;
    int startIndex = jsonResponse.indexOf("\"visibility\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String visibility = jsonResponse.substring(
        startIndex + length, endIndex);
        return Double.parseDouble(visibility);
      }
    }
    return Double.NaN;
  }

  // the same method as above except for wind dir
  public String getWindDir(final String jsonResponse) {
    final int length = 14;
    int startIndex = jsonResponse.indexOf("\"wind_degree\":");
    int degrees = 0;
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String windDir = jsonResponse.substring(startIndex + length, endIndex);
        degrees = Integer.parseInt(windDir);
      }
    }
    final int nPeak = 337;
    final int nSub = 22;
    final int neSub = 67;
    final int eSub = 112;
    final int seSub = 157;
    final int sSub = 202;
    final int swSub = 247;
    final int wSub = 292;
    // checks the degrees and returns the output in words rather than numbers
    if (degrees >= nPeak || degrees < nSub) {
      return "Northern";
    } else if (degrees >= nSub && degrees < neSub) {
      return "Northeastern";
    } else if (degrees >= neSub && degrees < eSub) {
      return "Eastern";
    } else if (degrees >= eSub && degrees < seSub) {
      return "Southeastern";
    } else if (degrees >= seSub && degrees < sSub) {
      return "Southern";
    } else if (degrees >= sSub && degrees < swSub) {
      return "Southwestern";
    } else if (degrees >= swSub && degrees < wSub) {
      return "Western";
    } else {
      return "Northwestern";
    }
  }

  // the same method as above except for location(needs three things)
  public String getLocation(final String jsonResponse) {
    String city = "";
    int length = 8;
    String country = "";
    String region = "";
    int startIndex = jsonResponse.indexOf("\"name\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        city = jsonResponse.substring(startIndex + length, endIndex - 1);
      }
    }
    length = 11;
    startIndex = jsonResponse.indexOf("\"country\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        country = jsonResponse.substring(startIndex + length, endIndex - 1);
      }
    }
    length = 10;
    startIndex = jsonResponse.indexOf("\"region\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        region = jsonResponse.substring(startIndex + length, endIndex - 1);
      }
    }
    return city + ", " + region + ", " + country;
  }

  // the same method as above except for time
  public String getTime(final String jsonResponse) {
    final int length = 13;
    int startIndex = jsonResponse.indexOf("\"localtime\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String time = jsonResponse.substring(startIndex + length, endIndex - 1);
        return time;
      }
    }
    return "";
  }

  // the same method as above except for weather description
  public String getDescription(final String jsonResponse) {
    final int length = 25;
    int startIndex = jsonResponse.indexOf("\"weather_descriptions\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String description = jsonResponse.substring(
          startIndex + length, endIndex - 2);
        return description;
      }
    }
    return "";
  }

  // the same method as above except for feels like temperature
  public double getFeelsLikeTemperature(final String jsonResponse) {
    final int length = 12;
    int startIndex = jsonResponse.indexOf("\"feelslike\":");
    if (startIndex != -1) {
      int endIndex = jsonResponse.indexOf(",", startIndex);
      if (endIndex != -1) {
        String feelsLikeString = jsonResponse.substring(
          startIndex + length, endIndex);
        return Double.parseDouble(feelsLikeString);
      }
    }
    return Double.NaN;
  }
}
