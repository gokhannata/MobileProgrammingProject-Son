package com.example.mobileprogrammingproject;

public class Kullanicilar {
    public Kullanicilar() {

    }

    public Kullanicilar(String username, String password,int imgSrc) {
        this.username = username;
        this.password = password;
        this.imgSrc=imgSrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImgSrc(){ return imgSrc; }

    public void setImgSrc(int imgSrc){ this.imgSrc=imgSrc; }

    int id;
    String username;
    String password;
    int imgSrc;
}
