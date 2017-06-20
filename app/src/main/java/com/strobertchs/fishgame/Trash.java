package com.strobertchs.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Alex on 6/15/2017.
 */

public class Trash extends AnimatedSprite {

    int trashWidth;
    int trashHeight;
    int positionY;
    Random randomNumber = new Random();
    int positionX;
    Rect destRect;
    Bitmap bitmap;

    public Trash(Context context, int screen_width, int screen_height){
        setHeight(screen_height / 12);
        setWidth(screen_width/ 8);
        //positionX = randomNumber.nextInt(screen_width - trashWidth) + 1;
        setPositionX(screen_width  / 2 );
        //positionY = randomNumber.nextInt(screen_height - trashHeight) + 1;
        setPositionY(screen_height / 2);
        setUp_amount(20);
        setDown_amount(10);

        bitmap  = BitmapFactory.decodeResource(context.getResources(),R.drawable.cann);
    }

    public void updatePosition(){
        if(isMovingUp()){
            setPositionY(getPositionY() - getUp_amount() );
        }
        if(isMovingDown()){
            setPositionY((getPositionY() + getDown_amount()));
        }
    }

    public void verticleStop(){
        setMovingUp(false);
        setMovingDown(false);
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
