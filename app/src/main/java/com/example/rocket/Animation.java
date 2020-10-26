package com.example.rocket;

import android.graphics.Bitmap;

/**
 * Created by joey2 on 9-9-2015.
 */
public class Animation {

    private Bitmap frames[];
    private Bitmap image;
    private long start_time;
    private long delay_time;
    private long elapsed_time;
    private boolean played_once=false;

    private int current_frame=0;


    public Animation(Bitmap frames[], int delay_time_millis) {
        this.delay_time=1000000*delay_time_millis;
        this.frames=frames;

        start_time=System.nanoTime();
    }

    public void update(){
        elapsed_time = (System.nanoTime()-start_time);
        if(elapsed_time>delay_time) {
            current_frame++;
            start_time=System.nanoTime();
        }

        if(current_frame==frames.length){
            current_frame=0;
            played_once=true;
        }
    }
    public Bitmap getImage(){
        return frames[current_frame];
    }
    public boolean isPlayed_once(){
        return played_once;
    }
}
