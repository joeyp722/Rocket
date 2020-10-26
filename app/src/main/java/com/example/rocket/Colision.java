package com.example.rocket;

/**
 * Created by joey2 on 10-9-2015.
 */
public class Colision {
    private Rect object1;
    private Rect object2;

    public Colision(Rect object1,Rect object2){
        this.object1 = object1;
        this.object2 = object2;
    }

    public boolean getColisionBorder(){
        if(object1.getLocation1().getX()<object2.getLocation1().getX()) return true;
        if(object1.getLocation1().getY()<object2.getLocation1().getY()) return true;
        if(object1.getLocation2().getX()>object2.getLocation2().getX()) return true;
        if(object1.getLocation2().getY()>object2.getLocation2().getY()) return true;
        return false;
    }
    public boolean getColision(){

        if(object1.getLocation2().getX()>object2.getLocation1().getX()&&object2.getLocation2().getX()>object1.getLocation1().getX()){
            if(object1.getLocation2().getY()>object2.getLocation1().getY()&&object2.getLocation2().getY()>object1.getLocation1().getY()) return true;
        }
        return false;
    }
}
