package com.example.rocket;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by joey2 on 11-9-2015.
 */
public class Score {
    private int score;
    private Paint paint;
    int x,y;

    public Score(int score){
        this.score=score;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        paint.setAlpha(90);
        x=350;
        y=0;

    }
    public int getScore(){return score;}
    public void draw(Canvas canvas){


        canvas.drawText("Score: " + Integer.toString(score),x,y+paint.getTextSize(),paint);
    }
}
