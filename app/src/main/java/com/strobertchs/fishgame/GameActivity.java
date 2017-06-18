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
import android.view.MotionEvent;
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
    Canvas canvas;
    SurfaceHolder ourHolder;
    Paint paint;
    //set up basic variable
    Fish fish;
    Fisher fisher;
    Trash trash;
    int score;
    int screenWidth;
    int screenHeight;


    public GameActivity(Context context, int sScreenWidth, int sScreenHeight) {
        super(context);
        fisher = new Fisher(sScreenWidth, sScreenHeight);
        fish = new Fish(sScreenWidth, sScreenHeight);
        trash = new Trash(sScreenWidth, sScreenHeight);

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
            updateCourt();
        }

    }

    //collision




    public void updateCourt(){
        //to control the fisher is only moving inside the screen
        if(fisher.isMovingRight()){
            if (fish.getPositionX() + fish.getWidth() < screenWidth) {
                fisher.updatePosition();
            }
        }

        if(fisher.isMovingLeft()){
            if(fish.getPositionX() > 0){
                fisher.updatePosition();
            }
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

    public void drawCourt() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();


            //Paint paint = new Paint();
            canvas.drawColor(Color.BLACK);//the background
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(45);

            //draw fisher
            fisher.draw(canvas);

            //draw fish(background use)
            fish.draw(canvas);

            //draw trash

            System.out.println(fisher.toString());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (motionEvent.getX() >= screenWidth / 2) {
                    fisher.moveRight();
                } else {
                    fisher.moveLeft();
                }
                break;

            case MotionEvent.ACTION_UP:
                fisher.stop();
                break;
        }
        return true;
    }


    public void pause() {
        playGame = false;
        try {
            ourThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playGame = true;
        ourThread = new Thread(this);
        ourThread.start();
    }

}
