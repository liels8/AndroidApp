package com.example.newproj.models;

public class Meeting {


    private String Location;
    private String Date;
    private String Hour;
    private String DogType;
    private String Discription;
    private String Owner;

    public Meeting(String location, String date, String hour, String dogType, String discription, String owner) {
        Location = location;
        Date = date;
        Hour = hour;
        DogType = dogType;
        Discription = discription;
        Owner = owner;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public void setDogType(String dogType) {
        DogType = dogType;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getLocation() {
        return Location;
    }

    public String getDate() {
        return Date;
    }

    public String getHour() {
        return Hour;
    }

    public String getDogType() {
        return DogType;
    }

    public String getDiscription() {
        return Discription;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }


}
