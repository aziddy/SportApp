package com.example.alexander.sportapp;

import android.graphics.Bitmap;

/**
 * Created by alexander on 12/29/2015.
 */
public class FindEventData {

    public String sport;
    public String name;
    public String time;
    public String location;
    public String attending;
    public int image;
    public String lat;
    public String lng;

                                                                                                  // decode and scale image
    public FindEventData(String sport, String name, String time, String location, String attending, int image, String lat, String lng) {

        this.sport = sport;
        this.name = name;
        this.time = time;
        this.location = location;
        this.attending = attending;
        this.image = image;
        this.lat = lat;
        this.lng = lng;

    }





}
