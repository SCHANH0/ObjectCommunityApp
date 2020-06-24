package com.example.objectcommunityapp;

public class Memberinfo {
    private String name;
    private String phone;
    private String date;
    private String address;

    public Memberinfo(String name, String phone, String date, String address) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.address = address;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
