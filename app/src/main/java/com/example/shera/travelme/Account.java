package com.example.shera.travelme;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shera on 06/14/2016.
 */
public class Account {

    private String id;
    private String userId;
    private float creditBalance;
    private Date createdDate;
    private Date modifiedDate;

    public Account(){
        id = "";
        userId = "";
        creditBalance = 0;
        createdDate = null;
        modifiedDate = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(float creditBalance) {
        this.creditBalance = creditBalance;
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

    public String getModifiedDateToString(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(getModifiedDate());

    }
}
