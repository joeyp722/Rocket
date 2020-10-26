package com.example.rocket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

/**
 * Created by joey2 on 9-9-2015.
 */
public class Viking {
    private int x,y;
    private int width,height;
    private Bitmap image;

    public Viking(Bitmap image, int x, int y) {
        this.width=100;
        this.height=100;
        this.image=Bitmap.createScaledBitmap(image,width,height,false);
        this.x=x;
        this.y=y;
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(image,x,y,null);
    }
    public void update() {
    }

    public void sound(){

    }
    public com.example.rocket.Rect getRect(){
        return new com.example.rocket.Rect(new Vector(x+width,y+height), new Vector(x,y));
    }

}
