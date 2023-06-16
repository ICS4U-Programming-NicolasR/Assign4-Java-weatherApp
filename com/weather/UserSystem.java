package com.weather;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserSystem {
  // create a map of the users to authenticate
  private Map<String, User> users;
  // store all the data in a txt file
  private static final String USER_DATA_FILE = "users.txt";

  public UserSystem() {
    // create a new hash map that will keep all the user info inside it
    users = new HashMap<>();
    // load the data into the hash map
    loadUserData();
  }

  public void addUser(String username, String password) {
    // check to see if the user exists already.
    User user = users.get(username);
    // if they don't create a new user and add it to the hashmap
    if (user == null) {
      User newUser = new User(username, password);
      users.put(username, newUser);
      saveUserData();
    }
  }

  // authenticate if the passwords match
  public boolean authenticateUser(String username, String password) {
    User user = users.get(username);
    return user != null && user.getPassword().equals(password);
  }

  // set the user email
  public String setUserEmail(String username, String email) {
    // first see if the user exists
    User user = users.get(username);
    // if there is no email and the user exists set the new email then return that same email
    if (user != null && user.getEmail() == null) {
      user.setEmail(email);
      saveUserData();
    }
    return user.getEmail();
  }

  // method to load the data from the file
  private void loadUserData() {
    // try to read from file
    try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
      String line;
      // load the users from the file into the hashmap and create actual objects for them
      while ((line = reader.readLine()) != null) {
        // seperate eaach part by a ","
        String[] parts = line.split(",");
        if (parts.length == 3) {
          String username = parts[0];
          String password = parts[1];
          String email = parts[2];
          User user = new User(username, password);
          user.setEmail(email);
          users.put(username, user);
        }
      }
    } catch (IOException e) {
      System.out.println("Error loading user data: " + e.getMessage());
    }
  }

  // method used to save the user data to file
  private void saveUserData() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
      // for every entry into the map add it to the file.
      for (Map.Entry<String, User> entry : users.entrySet()) {
        String username = entry.getKey();
        User user = entry.getValue();
        String password = user.getPassword();
        String email = user.getEmail();
        writer.write(username + "," + password + "," + email);
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println("Error saving user data: " + e.getMessage());
    }
  }
}
