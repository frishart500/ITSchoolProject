package com.example.itschoolproject;

public class User {
    private String birhday, email, firstname, phone;

    public User(){

    }
    public User(String birhday, String email, String firstname, String phone) {
        this.birhday = birhday;
        this.email = email;
        this.firstname = firstname;
        this.phone = phone;

    }

    public String getBirhday() {
        return birhday;
    }

    public void setBirhday(String birhday) {
        this.birhday = birhday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
