package com.strobertchs.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Alex on 6/13/2017.
 */

public class Fisher extends AnimatedSprite {
    Bitmap bitmap;
    Rect destRect;

    public Fisher (Context context, int screen_width, int screen_height){
        super();
        setWidth(screen_width/5);
        setHeight(screen_height/ 15);
        setPositionX(screen_width/2);
        setPositionY(100);

        setHorizontal_amount(20);

        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.fisher);

    }

    public void draw(Canvas source_canvas){
        // draw rectangle right now to test if the app is running.
        // change to drawable later

        destRect = new Rect(
                getPositionX(),
                getPositionY(),
                getPositionX() + getWidth(),
                getPositionY() + getHeight()
        );
        source_canvas.drawBitmap(bitmap, null, destRect, null);
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
