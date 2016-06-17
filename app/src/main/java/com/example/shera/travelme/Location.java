package com.example.shera.travelme;

import java.util.Date;

/**
 * Created by shera on 06/14/2016.
 */
public class Location {

    private String id;
    private String name;
    private String latitude;
    private String longitude;
    private Date createdDate;

    public Location(){
        id = "";
        name = "";
        latitude = "";
        longitude = "";
        createdDate = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}

