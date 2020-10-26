package com.example.rocket;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

/**
 * Created by joey2 on 9-9-2015.
 */
public class Rocket {

    private int width_frames;
    private int height_frames;
    private int width_image;
    private int height_image;
    private Animation animation;
    private Bitmap frames[];
    private int number_of_frames;
    private int time_between_frames;
    private MediaPlayer mediaplayer;
    private int x,y;
    private int x_prev,y_prev;
    private int x_draw, y_draw;
    private float angle,draw_angle;
    private Vector velocity;
    private Parameters parameters = new Parameters();

    public Rocket(Bitmap start_image,MediaPlayer mediaplayer,Vector velocity){
        this.width_image = start_image.getWidth();
        this.height_image = start_image.getHeight();
        this.width_frames = start_image.getWidth();
        this.height_frames = 15;
        this.number_of_frames = 13;
        this.time_between_frames = 100;
        this.mediaplayer=mediaplayer;
        this.velocity = velocity;

        x = parameters.getRocket_start_location().getX();
        y= parameters.getRocket_start_location().getY();


        mediaplayer.setLooping(true);
        mediaplayer.start();

        frames = new Bitmap[number_of_frames];

        for(int i=0; i<number_of_frames;i++){
            frames[i] = Bitmap.createBitmap(start_image,0, i*height_frames,width_frames,height_frames);
        }
        this.animation = new Animation(frames, time_between_frames);
    }

    public void draw(Canvas canvas){

        Bitmap image = animation.getImage();
        canvas.rotate(angle);
        canvas.drawBitmap(image, x_draw, y_draw, null);
        canvas.rotate(-angle);

    }

    public void update(){
        animation.update();
        x_prev=x;
        y_prev=y;

        velocity=new Vector(velocity.getX()+parameters.getGravity().getX(),velocity.getY()+ parameters.getGravity().getY());
        x+=velocity.getX();
        y+=velocity.getY();

        angle=(float)Math.toDegrees(Math.atan(((double)(y-y_prev))/(x-x_prev)));
        if(velocity.getX()>=0) angle+=180;

        x_draw=(int)(x*Math.cos(Math.toRadians(-angle))-y*Math.sin(Math.toRadians(-angle)));
        y_draw=(int)(x*Math.sin(Math.toRadians(-angle))+y*Math.cos(Math.toRadians(-angle)));
    }

    public void sound() {

    }
    public void soundRelease(){
        mediaplayer.release();
    }
    public void vibrate(){}

    public Rect getRect(){
        return new Rect(new Vector(x+width_frames,y+height_frames), new Vector(x,y));
    }
    public Vector getLocation(){
        return new Vector(x,y);
    }
}
