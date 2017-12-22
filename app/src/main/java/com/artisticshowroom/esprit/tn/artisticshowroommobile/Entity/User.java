package com.artisticshowroom.esprit.tn.artisticshowroommobile.Entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by mustapha- on 16/04/2017.
 */

public class User implements Serializable {

    private int Id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private int phone;
    private int age;
    private String mail;
    private String pictureUrl;
    private int confirm;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }


    public User(JSONObject j) {

        this.Id=j.optInt("id");
        this.firstName=j.optString("firstName");
        this.lastName=j.optString("lastName");
        this.age=j.optInt("age");
        this.login=j.optString("login");
        this.password=j.optString("password");
        this.confirm=j.optInt("confirm");
        this.phone=j.optInt("phone");
        this.pictureUrl=j.optString("pictureUrl");




    }
}
