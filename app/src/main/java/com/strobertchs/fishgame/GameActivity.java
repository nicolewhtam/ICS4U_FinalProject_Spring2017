package com.strobertchs.fishgame;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by Alex on 6/13/2017.
 */

class GameActivity extends SurfaceView implements Runnable{

    volatile boolean playGame;
    int fps;
    long lastFrameTime;
    Thread ourThread = null;

    public GameActivity(Context context, int sScreenWidth, int sScreenHeight) {
        super(context);
    }



    @Override
    public void run() {
        while (playGame) {
            controlFPS();
            //soundPool2.play(starwar, 1, 1, 0, 0, 1);
        }

    }

    public void controlFPS() {
        long timeThisFrame = (System.currentTimeMillis() - lastFrameTime);
        long timeToSleep = 15 - timeThisFrame;
        if (timeThisFrame > 0) {
            fps = (int) (1000 / timeThisFrame);
        }
        if (timeToSleep > 0) {
            try {
                ourThread.sleep(timeToSleep);
            } catch (InterruptedException e) {
            }
        }
        lastFrameTime = System.currentTimeMillis();
    }
}
