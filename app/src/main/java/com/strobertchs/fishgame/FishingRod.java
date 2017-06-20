package com.strobertchs.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Alex on 6/19/2017.
 */

public class FishingRod extends AnimatedSprite {

    Bitmap bitmap;
    Rect destRect;


    public FishingRod(Context context, int screenWidth, int screenHeight){
        setPositionX(screenWidth/ 2);
        setPositionY(180);
        setWidth(screenWidth/10);
        setHeight(screenHeight/25);
        setDown_amount(20);
        setUp_amount(20);
        setHorizontal_amount(10);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fishhook);
    }

    public void stop(){
        setMovingRight(false);
        setMovingLeft(false);
    }

    public void verticleStop(){
        setMovingDown(false);
        setMovingUp(false);
    }

    public void updatePosition(){
        if(isMovingUp()){
            setPositionY(getPositionY() - getUp_amount());
        }
        if(isMovingDown()){
            setPositionY((getPositionY() + getDown_amount()));
        }
        if(isMovingRight()){
            setPositionX(getPositionX() + getHorizontal_amount());
        }
        if(isMovingLeft()){
            setPositionX(getPositionX() - getHorizontal_amount());
        }
    }


    public void draw(Canvas canvas){
        destRect = new Rect(
                getPositionX(),
                getPositionY(),
                getPositionX() + getWidth(),
                getPositionY() + getHeight()
        );

        canvas.drawBitmap(bitmap, null, destRect, null);
    }
}
