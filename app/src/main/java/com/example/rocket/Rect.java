package com.example.rocket;

/**
 * Created by joey2 on 10-9-2015.
 */

public class Rect {
    private Vector location1;
    private Vector location2;
    public Rect(Vector location2, Vector location1){
        this.location1 = location1;
        this.location2 = location2;
    }
    public Vector getLocation1(){
        return location1;
    }
    public Vector getLocation2(){
        return location2;
    }

}
