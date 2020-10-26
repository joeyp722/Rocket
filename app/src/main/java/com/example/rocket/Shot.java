package com.example.rocket;

import android.media.MediaPlayer;

/**
 * Created by joey2 on 10-9-2015.
 */
public class Shot {
    private Vector location_difference;
    private Vector velocity;
    private MediaPlayer mediaplayer;
    private double velocity_scalefactor_x,velocity_scalefactor_y;
    private Parameters parameters = new Parameters();

    public Shot(Vector location_difference,MediaPlayer mediaplayer){
        this.location_difference = location_difference;
        this.mediaplayer = mediaplayer;
        velocity_scalefactor_x = parameters.getVelocity_scalefactor_x();
        velocity_scalefactor_y = parameters.getVelocity_scalefactor_y();
        this.velocity = new Vector((int)(velocity_scalefactor_x*location_difference.getX()),(int)(-velocity_scalefactor_y*location_difference.getY()));


        mediaplayer.start();
    }
    public Vector getVelocity(){
        return velocity;
    }

}
