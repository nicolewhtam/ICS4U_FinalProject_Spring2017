package com.strobertchs.fishgame;

import android.graphics.Canvas;

/**
 * Created by Alex on 6/13/2017.
 */

public class Fisher extends AnimatedSprite {
    public Fisher (int screen_width){
        super();
        setWidth(screen_width/15);
        setPositionX(screen_width/2);
        setPositionY(10);

        setHorizontal_amount(20);
    }

    public void draw(Canvas source_canvas){
        // draw rectangle right now to test if the app is running.
        // change to drawable later
        source_canvas.drawRect(getPositionX(),getPositionY(),getPositionX()+getWidth(), getPositionY() + getWidth(),paint);
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
}
