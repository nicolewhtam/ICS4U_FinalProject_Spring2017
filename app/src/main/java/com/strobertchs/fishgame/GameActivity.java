package com.strobertchs.fishgame;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

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
    FishingRod fishingRod;
    Drawable background;
    boolean hookOut;
    int score;
    int screenWidth;
    int screenHeight;
    int rNumber;

    public GameActivity(Context context, int sScreenWidth, int sScreenHeight) {
        super(context);
        fisher = new Fisher(context, sScreenWidth, sScreenHeight);
        fish = new Fish(context, sScreenWidth, sScreenHeight);
        trash = new Trash(context, sScreenWidth, sScreenHeight);
        fishingRod = new FishingRod(context, sScreenWidth, sScreenHeight);
        background = getResources().getDrawable(R.drawable.ocean);
        background.setBounds(0, 0, sScreenWidth, sScreenHeight);
        fishingRod.stop();
        hookOut = false;

        screenHeight = sScreenHeight;
        screenWidth = sScreenWidth;
        ourHolder = getHolder();
        paint = new Paint();
        score = 0;
    }



    @Override
    public void run() {
        while (playGame) {
            controlFPS();
            drawCourt();
            updateCourt();
        }

    }


    public void updateCourt(){
        //to control the fisher is only moving inside the screen
        if(fisher.isMovingRight()){
            if (fisher.getPositionX() + fisher.getWidth() < screenWidth) {
                fisher.updatePosition();
            }
        }

        if(fisher.isMovingLeft()){
            if(fisher.getPositionX() > 0){
                fisher.updatePosition();
            }
        }
        //to control the fishing rod is only moving inside the screen
        if(fishingRod.isMovingRight()){
            if(fishingRod.getPositionX() + fishingRod.getWidth() + 20< screenWidth){
                fishingRod.updatePosition();
            }
        }

        if(fishingRod.isMovingLeft()){
            if(fishingRod.getPositionX() - 10 > 0){
                fishingRod.updatePosition();
            }
        }
        if (fishingRod.isMovingDown()) {
            if(fishingRod.getPositionY() + fishingRod.getHeight() > screenHeight){
                fishingRod.moveUp();
                fishingRod.updatePosition();
            }
        }
        //control the trash is in the screen
        if(trash.isMovingDown()){
            if(trash.getPositionY() + trash.getHeight() > screenHeight){
                trash.updatePosition();
                trash.verticleStop();
            }
        }

        //collision
        //fishing rod collision with trash
        if(fishingRod.getPositionY() + fishingRod.getHeight() >= trash.getPositionY() && fishingRod.getPositionY() + fishingRod.getHeight() <= trash.getPositionY() + trash.getHeight()) {
            if (fishingRod.getPositionX() + fishingRod.getWidth() >= trash.getPositionX() && fishingRod.getPositionX() <= trash.getPositionX() + trash.getWidth() && fishingRod.isMovingDown()) {
                trash.moveUp();
                fishingRod.moveUp();
                fishingRod.stop();
                fisher.stop();

            }
        }

        //collect trash
        if(fishingRod.getPositionY() <= fisher.getPositionY() && fishingRod.isMovingUp()){
            fishingRod.verticleStop();
            fishingRod.setPositionY(180);
            hookOut = false;
        }
        for(int i = 0; i < 10; i++ ){
            if(trash.getPositionY() <= fisher.getPositionY() + fisher.getHeight() + 20 && trash.isMovingUp()) {
                score += 100;
                trash.verticleStop();
                fishingRod.verticleStop();
                fishingRod.setPositionY(180);
                hookOut = false;
                Random randomNumber = new Random();
                rNumber = randomNumber.nextInt(screenWidth - fish.getWidth());
                trash.setPositionY(400);
                trash.setPositionX(rNumber);
                trash.moveDown();
                if(i == 10) {
                    i = 0;
                }
//                int trashPosition = randomNumber.nextInt(5);
//
//                switch (trashPosition) {
//                    case 1:
//                        trash.setPositionX(190);
//                        trash.setPositionY(screenHeight - 200);
//                        break;
//
//                    case 2:
//                        trash.setPositionX(300);
//                        trash.setPositionY(screenHeight - 100);
//                        break;
//
//                    case 3:
//                        trash.setPositionX(100);
//                        trash.setPositionY(screenHeight / 3);
//                        break;
//
//                    case 4:
//                        trash.setPositionX(400);
//                        trash.setPositionY(700);
//                        break;
//
//                    case 5:
//                        trash.setPositionX(250);
//                        trash.setPositionY(screenHeight / 2);
               // }
            }

        }
        trash.updatePosition();
        fishingRod.updatePosition();
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
            canvas.drawColor(Color.BLACK);

            //draw background
            background.draw(canvas);

            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(45);
            canvas.drawText("Score:" + score + " fps:" + fps, 20, 40, paint);

            //draw fishing rod
            fishingRod.draw(canvas);

            //draw fisher
            fisher.draw(canvas);

            //draw fish(background use)
            //fish.draw(canvas);


            //draw trash
            trash.draw(canvas);
            ourHolder.unlockCanvasAndPost(canvas);

            System.out.println(fisher.toString());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (motionEvent.getX() >= screenWidth / 2 && motionEvent.getY() <= screenHeight/2 && hookOut == false) {
                    fisher.moveRight();
                    fishingRod.moveRight();
                } else if(motionEvent.getX() <= screenWidth/ 2 && motionEvent.getY() <= screenHeight / 2 && hookOut == false){
                    fisher.moveLeft();

                    fishingRod.moveLeft();
                }else if(motionEvent.getY()> screenHeight /2 && hookOut == false){
                    fishingRod.moveDown();
                    //fishingRod.updatePosition();
                    hookOut = true;
                }

                break;

            case MotionEvent.ACTION_UP:
                fisher.stop();
                fishingRod.stop();
                break;
        }
        return true;
    }

//  bug
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
