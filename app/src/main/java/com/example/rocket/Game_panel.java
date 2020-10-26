package com.example.rocket;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
// import android.support.annotation.MainThread;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by joey2 on 8-9-2015.
 */
public class Game_panel extends SurfaceView implements SurfaceHolder.Callback{
    private ArrayList<Viking> viking;
    private Background background;
    private Bitmap background_image[];
    private Main_thread thread;
    private Vibrator vibrator;
    private ArrayList<Rocket> rocket;
    private ArrayList<Explosion> explosion;
    private Context context;
    private Vector location_down;
    private Vector location_up;
    private Border border;
    private Score score;
    private Circle circle;



    public Game_panel(Context context,Vibrator vibe){
        super(context);
        getHolder().addCallback(this);
        this.vibrator=vibe;
        this.context=context;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        background_image = new Bitmap[4];

        background_image[0]=BitmapFactory.decodeResource(getResources(),R.drawable.background_galaxy_i0);
        background_image[1]=BitmapFactory.decodeResource(getResources(),R.drawable.background_galaxy_i1);
        background_image[2]=BitmapFactory.decodeResource(getResources(),R.drawable.background_galaxy_i2);
        background_image[3]=BitmapFactory.decodeResource(getResources(),R.drawable.background_galaxy_i3);

        background = new Background(background_image,MediaPlayer.create(context,R.raw.background_music),500,500);
        circle = new Circle();
        viking = new ArrayList<Viking>();
        viking.add(new Viking(BitmapFactory.decodeResource(getResources(), R.drawable.viking),new Random().nextInt(400),new Random().nextInt(400)));
        rocket = new ArrayList<Rocket>();
        explosion = new ArrayList<Explosion>();
        border = new Border();
        score = new Score(0);

        thread = new Main_thread(getHolder(),this);
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        thread.setRunning(false);
        System.exit(0);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Shot shot;
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            location_down =  new Vector((int)event.getRawX(),(int)event.getRawY());
        }
        if(event.getAction() == MotionEvent.ACTION_UP){

            location_up = new Vector((int) event.getRawX(), (int) event.getRawY());
            shot = new Shot(new Vector(location_down.getX() - location_up.getX(), -(location_down.getY() - location_up.getY())), MediaPlayer.create(context, R.raw.shot));
            rocket.add(new Rocket(BitmapFactory.decodeResource(getResources(), R.drawable.rocket), MediaPlayer.create(context, R.raw.rocket), shot.getVelocity()));
        }

        return true;

    }
    public void draw(){
        Canvas canvas = getHolder().lockCanvas();
        canvas.scale(((float) getWidth() / background.getWidth()), (((float) getHeight() / background.getHeight())));

        background.draw(canvas);
        for(Viking vk:viking){
            vk.draw(canvas);
        }
        circle.draw(canvas);
        for(Rocket r: rocket){
            r.draw(canvas);
        }
        for(Explosion expl:explosion){
            expl.draw(canvas);
        }
        score.draw(canvas);

        getHolder().unlockCanvasAndPost(canvas);
    }
    public void update() {
        background.update();
        for(Viking vk:viking){
            vk.update();
        }
        for(Rocket r:rocket){
            r.update();
        }
        for(Explosion expl:explosion){
            expl.update();
        }
    }
    public void vibrate() {
        // rocket.vibrate();
    }
    public void sound(){
        background.sound();
        for(Viking vk:viking){
            vk.sound();
        }
        for(Rocket r:rocket){
            r.sound();
        }
        for(Explosion expl:explosion){
            expl.sound();
        }
    }
    public void destroy(){
        Colision colision;

        for(Rocket r: rocket){
            colision = new Colision(r.getRect(),border.getRect());
            if(colision.getColisionBorder()){
                explosion.add(new Explosion(BitmapFactory.decodeResource(getResources(),R.drawable.explosion),MediaPlayer.create(context, R.raw.explosion),r.getLocation(),vibrator));
                r.soundRelease();
                rocket.remove(r);
                break;
            }
        }
        for(Rocket r: rocket){
            for(Viking vk:viking) {
                colision = new Colision(r.getRect(), vk.getRect());
                if(colision.getColision()){
                    explosion.add(new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.explosion), MediaPlayer.create(context, R.raw.explosion), r.getLocation(),vibrator));
                    r.soundRelease();
                    rocket.remove(r);
                    viking.remove(vk);
                    viking.add(new Viking(BitmapFactory.decodeResource(getResources(), R.drawable.viking), new Random().nextInt(400), new Random().nextInt(400)));
                    score = new Score(score.getScore()+100);
                    break;
                }
            }
        }
        for(Explosion expl:explosion){
            if(expl.isPlayed_once()){
                explosion.remove(expl);
                break;
            }
        }
    }

}
