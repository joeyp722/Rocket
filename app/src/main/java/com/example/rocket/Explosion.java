package com.example.rocket;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Vibrator;

/**
 * Created by joey2 on 9-9-2015.
 */
public class Explosion {

    private Vector location;
    private int width_frames;
    private int height_frames;
    private int width_image;
    private int height_image;
    private Animation animation;
    private Bitmap frames[];
    private int number_of_frames;
    private int time_between_frames;
    private int row;
    private MediaPlayer mediaplayer;
    private Vibrator vibrator;
    private int explosion_time;

    public Explosion(Bitmap start_image, MediaPlayer mediaplayer,Vector location,Vibrator vibrator){
        this.width_image = start_image.getWidth();
        this.height_image = start_image.getHeight();
        this.location=location;
        this.width_frames = 100;
        this.height_frames = 100;
        this.number_of_frames = 25;
        this.time_between_frames = 10;
        this.mediaplayer = mediaplayer;
        this.vibrator = vibrator;
        this.explosion_time = 250;

        frames = new Bitmap[number_of_frames];

        for(int i = 0; i<frames.length; i++)
        {
            if(i%5==0&&i>0)row++;
            frames[i] = Bitmap.createBitmap(start_image, (i-(5*row))*width_frames, row*height_frames, width_frames, height_frames);
        }
        this.animation = new Animation(frames, time_between_frames);

        mediaplayer.start();
        vibrator.vibrate(explosion_time);
    }

    public void draw(Canvas canvas){

        Bitmap image = animation.getImage();
        canvas.rotate(0);
        canvas.drawBitmap(image,location.getX(),location.getY(),null);
    }

    public void update(){
        animation.update();
    }

    public void sound(){

    }

    public void vibrate(){}

    public boolean isPlayed_once(){
        return animation.isPlayed_once();
    }
}
