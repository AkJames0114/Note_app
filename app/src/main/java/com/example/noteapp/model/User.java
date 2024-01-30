package com.example.noteapp.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private Integer id;

    @SerializedName("email")
    private String email;

    @SerializedName("passcode")
    private String passcode;

    @SerializedName("first_name")
    private String firstname;

    @SerializedName("last_name")
    private String lastname;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("address")
    private String address;

    @SerializedName("device_token")
    private String deviceToken;

    @SerializedName("access_token")
    private String accessToken;

    public User(Integer id, String email, String passcode, String first_name, String last_name, String phoneNumber, String address, String deviceToken, String accessToken) {
        this.id = id;
        this.email = email;
        this.passcode = passcode;
        this.firstname = first_name;
        this.lastname = last_name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.deviceToken = deviceToken;
        this.accessToken = accessToken;
    }

    public User(String email, String password) {
        this.email = email;
        this.passcode = password;

    }

    public User() {

    }

    public User(String firstname, String lastname, String email, String passcode) {
        this.email = email;
        this.passcode = passcode;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return passcode;
    }

    public void setPassword(String passcode) {
        this.passcode = passcode;
    }

    public String getFirst_name() {
        return firstname;
    }

    public void setFirst_name(String firstname) {
        this.firstname = firstname;
    }

    public String getLast_name() {
        return lastname;
    }

    public void setLast_name(String last_name) {
        this.lastname = last_name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
