package com.example.yelperhelper;

public class Business {
    private String name;
    private String address;
    private String openHour;
    private String closeHour;
    private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public float getRating() {
        return rating;
    }

    private float rating;

    public Business(String name, String address, String phoneNumber, float rating){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
