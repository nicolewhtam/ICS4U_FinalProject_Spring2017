package com.strobertchs.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import static android.R.attr.bitmap;
import static android.media.CamcorderProfile.get;

/**
 * Created by Alex on 6/16/2017.
 */

public class Fish extends Sprite {

    protected Bitmap bitmap;
    protected ArrayList<Bitmap> fish;
    protected Rect destRect;

    public Fish(Context context, int screen_width, int screen_height){
        setPositionX(screen_width/2);
        setPositionY(screen_height / 5);
        setWidth(screen_width / 8);
        setHeight(screen_height/12);

        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.fish1);
        fish = new ArrayList<Bitmap>();
        fish.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.fish1));
        fish.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.fish2));
        fish.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.fish3));
        fish.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.fish4));
    }

    public String toString(){
        return "Fish position X -> " + getPositionX() + "Fish position Y ->" + getPositionY();
    }

    public void draw(Canvas canvas){

        for( int i = 0; i < fish.toString().length(); i++ ){
            destRect = new Rect(
                    getPositionX(),
                    getPositionY(),
                    getPositionX() + getWidth(),
                    getPositionY() + getHeight()
            );

            this.setPositionX(this.getPositionX() + 20);
            this.setPositionY(this.getPositionY() + 20);

            canvas.drawBitmap(fish.get(i), null, destRect, null);
        }

    }
}
