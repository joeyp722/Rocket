package com.example.rocket;

/**
 * Created by joey2 on 10-9-2015.
 */
public class Parameters {

    public Vector rocket_start_location;
    public double velocity_scalefactor_x;
    public double velocity_scalefactor_y;
    public Vector gravity;

    public Parameters(){
        this.rocket_start_location = new Vector(100,400);
        this.velocity_scalefactor_x = 1;
        this.velocity_scalefactor_y = 1;
        this.gravity = new Vector(0,1);
    }
    public Vector getRocket_start_location(){return rocket_start_location;}
    public double getVelocity_scalefactor_x(){return velocity_scalefactor_x;}
    public double getVelocity_scalefactor_y(){return velocity_scalefactor_y;}
    public Vector getGravity(){return gravity;}

}
