package com.example.rocket;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by joey2 on 11-9-2015.
 */
public class Circle {
    private Paint paint;
    private int r;
    private Vector location;
    private Parameters parameters;

    public Circle(){
        this.parameters = new Parameters();
        this.paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(50);
        this.location = parameters.getRocket_start_location();
        this.r=50;

    }
    public void draw(Canvas canvas){
        canvas.drawCircle(location.getX(),location.getY(),r,paint);
    }
    public int getR(){return r;}
    public boolean isInCircle(Vector location){
        if(((this.location.getX()-getR())< location.getX())&&((location.getX())<(this.location.getX()+getR()))&&((this.location.getY()-getR())< location.getY())&&(location.getY()<(this.location.getY()+getR()))) return true;
        return false;
    }
}
