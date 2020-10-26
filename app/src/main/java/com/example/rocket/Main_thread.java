package com.example.rocket;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.SurfaceHolder;

/**
 * Created by joey2 on 9-9-2015.
 */
public class Main_thread extends Thread {

    public static double framerate=30;
    private long start_time;
    private long target_time;
    private long total_time;
    private long wait_time;
    private long elapsed_time;
    private Game_panel game_panel;
    private boolean running;

    SurfaceHolder holder;

    public Main_thread(SurfaceHolder holder, Game_panel game_panel){
        super();
        this.holder = holder;
        this.game_panel = game_panel;
    }
    //@Override
    public void run(){

        //vibrator.vibrate(1000);
        target_time = (long)(1000000000.0/ framerate);

        while(running){
            start_time = System.nanoTime();
            game_panel.update();
            game_panel.draw();
            game_panel.vibrate();
            game_panel.sound();
            game_panel.destroy();

            elapsed_time = System.nanoTime()-start_time;
            wait_time = target_time-elapsed_time;
            try {
                this.sleep(wait_time/1000000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setRunning(boolean bool){
        running=bool;
    }
}
