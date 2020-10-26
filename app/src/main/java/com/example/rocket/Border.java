package com.example.rocket;

/**
 * Created by joey2 on 10-9-2015.
 */
public class Border {
    int x1,x2,y1,y2;
    private Rect border;
    public Border(){
        x1 = 0;
        y1 = 0;
        x2 = 500;
        y2 = 500;
        this.border = new Rect(new Vector(x2,y2),new Vector(x1,y1));
    }
    public Rect getRect(){
        return border;
    }
}
