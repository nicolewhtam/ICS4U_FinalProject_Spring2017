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
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Alex on 6/13/2017.
 */

class GameActivity extends SurfaceView implements Runnable{

    volatile boolean playGame;
    int fps;
    long lastFrameTime;
    Thread ourThread = null;
    Fisher fish;
    Canvas canvas;
    SurfaceHolder ourHolder;
    Paint paint;
    int score;
    int screenWidth;
    int screenHeight;


    public GameActivity(Context context, int sScreenWidth, int sScreenHeight) {
        super(context);
        fish = new Fisher(sScreenWidth, sScreenHeight);
        screenHeight = sScreenHeight;
        screenWidth = sScreenWidth;
        ourHolder = getHolder();
        paint = new Paint();
    }



    @Override
    public void run() {
        while (playGame) {
            controlFPS();
            drawCourt();
//            updateCourt();
        }

    }

    //collision



//    public void updateCourt(){
//        //to control the fisher is only moving inside the screen
//        if(fish.isMovingRight()){
//            if (fish.getPositionX() + fish.getWidth() < screenWidth) {
//                fish.updatePosition();
//            }
//        }
//
//        if(fish.isMovingLeft()){
//            if(fish.getPositionX() > 0){
//                fish.updatePosition();
//            }
//        }
//
//    }

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

    public void drawCourt() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();


            //Paint paint = new Paint();
            canvas.drawColor(Color.BLACK);//the background
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(45);

            fish.draw(canvas);
            System.out.println(fish.toString());
        }
    }
//
//    public void pause() {
//        playGame = false;
//        try {
//            ourThread.join();
//        } catch (InterruptedException e) {
//        }
//    }
//
//    public void resume() {
//        playGame = true;
//        ourThread = new Thread(this);
//        ourThread.start();
//    }
//


}
