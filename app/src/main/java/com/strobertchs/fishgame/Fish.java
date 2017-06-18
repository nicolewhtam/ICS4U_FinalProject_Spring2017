package com.strobertchs.fishgame;

import android.graphics.Canvas;

/**
 * Created by Alex on 6/16/2017.
 */

public class Fish extends Sprite {

    public Fish(int screen_width, int screen_height){
        setPositionX(screen_width/2);
        setPositionY(screen_height / 5);
        setWidth(screen_width / 8);
        setHeight(screen_height/12);
    }

    public String toString(){
        return "Fish position X -> " + getPositionX() + "Fish position Y ->" + getPositionY();
    }

    public void draw(Canvas canvas){
        canvas.drawRect( getPositionX(), getPositionY(), getPositionX() + getWidth(), getPositionY() + getHeight(), paint);
    }
}
