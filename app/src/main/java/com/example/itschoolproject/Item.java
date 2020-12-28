package com.example.itschoolproject;

public class Item {
    private String img;
    private String user;
    private String subject;

    public Item(int ic_launcher, String user, String subject) {
        this.img = img;
        this.user = user;
        this.subject = subject;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
