package com.strobertchs.fishgame;

import android.graphics.Canvas;

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

    public Trash(int screen_width, int screen_height){
        setHeight(screen_height / 12);
        setWidth(screen_width/ 8);
        //positionX = randomNumber.nextInt(screen_width - trashWidth) + 1;
        setPositionX(screen_width  / 2 );
        //positionY = randomNumber.nextInt(screen_height - trashHeight) + 1;
        setPositionY(screen_height / 2);
        setUp_amount(10);
    }

    public void updatePosition(){
        if(isMovingUp()){
            this.setPositionX(getPositionX() - getUp_amount() );
        }
    }

    public void draw(Canvas canvas){
        canvas.drawRect(getPositionX(), getPositionX(), getPositionX() + getWidth(), getPositionY() + getHeight(), paint);
    }

}
