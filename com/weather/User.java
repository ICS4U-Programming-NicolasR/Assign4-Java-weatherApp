package com.weather;

public class User {
  // fields
  private String username;
  private String password;
  private String email;

  // constructor
  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // multiple methods to get the different data types
  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
