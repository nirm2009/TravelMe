package com.example.shera.travelme;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;

/**
 * Created by shera on 06/14/2016.
 */
public class User {

    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private Bitmap image;
    private String email;
    private String password;
    private String type;
    private Location lastKnownLocation;
    private Date createdDate;

    public User(){
        id = "";
        userName = "";
        firstName = "";
        lastName = "";
        image = null;
        email = "";
        password = "";
        type = "";
        lastKnownLocation = null;
        createdDate = null;
        modifiedDate = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    private Date modifiedDate;


}
