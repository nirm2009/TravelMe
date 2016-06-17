package com.example.shera.travelme;

import java.util.Date;

/**
 * Created by shera on 06/14/2016.
 */
public class Journey {

    private String id;
    private Date startTimeStamp;
    private Date endTimeStamp;
    private float distance;
    private float price;
    private String nfcId;
    private Date createdDate;

    public Journey(){
        id = "";
        startTimeStamp = null;
        endTimeStamp = null;
        distance = 0;
        price = 0;
        nfcId = "";
        createdDate = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(Date startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public Date getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(Date endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNfcId() {
        return nfcId;
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
