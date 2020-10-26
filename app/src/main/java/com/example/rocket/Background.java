package com.example.rocket;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * Created by joey2 on 9-9-2015.
 */
public class Background {
    private Bitmap[] frames;
    private MediaPlayer mediaplayer;
    private Animation animation;
    private int width,height;
    private int time_between_frames;

    public Background(Bitmap frames[],MediaPlayer mediaplayer,int width,int height){
        this.frames = frames;
        this.width = width;
        this.height = height;
        this.time_between_frames = 200;
        for(int i=0;i<frames.length;i++){
            frames[i]=Bitmap.createScaledBitmap(frames[i],width,height,false);
        }

        this.animation = new Animation(frames,time_between_frames);
        this.mediaplayer = mediaplayer;
        mediaplayer.setLooping(true);
        mediaplayer.start();

    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),0,0,null);
    }
    public void update() {
        animation.update();
    }
    public void sound() {

    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

}
