package com.strobertchs.fishgame;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by Alex on 6/13/2017.
 */

public class Fisher extends AnimatedSprite {


    public Fisher (int screen_width, int screen_height){
        super();
        setWidth(screen_width/6);
        setHeight(screen_height/ 20);
        setPositionX(screen_width/2);
        setPositionY(100);

        setHorizontal_amount(20);
    }

    public void draw(Canvas source_canvas){
        // draw rectangle right now to test if the app is running.
        // change to drawable later
        source_canvas.drawRect(getPositionX(),getPositionY(),getPositionX()+getWidth(), getPositionY() + getHeight(),paint);
    }

    public void stop(){
        setMovingLeft(false);
        setMovingRight(false);
    }

    public void updatePosition(){
        if(isMovingRight()){
            setPositionX(getPositionX() + getHorizontal_amount());
        }
        if(isMovingLeft()){
            setPositionX(getPositionX() - getHorizontal_amount());
        }
    }

    public String toString(){
        return "The fisher's X ->" + Integer.toString(getPositionX()) + "The Fisher's Y ->" + Integer.toString(getPositionY());
    }
}
