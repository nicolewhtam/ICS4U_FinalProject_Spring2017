package com.strobertchs.fishgame;

import android.app.Activity;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

//Activity for the game screen, will change to start screen later.
public class MainActivity extends Activity {

    GameActivity FishGame;
    //For getting display details like the number of pixels
    Display display;
    Point size;
    int screenWidth;
    int screenHeight;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        FishGame = new GameActivity(this, screenWidth, screenHeight);
        setContentView(FishGame);

    }
    @Override
    protected void onStop() {
        super.onStop();
        while (true) {
            FishGame.pause();
            break;
        }
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        FishGame.pause();
        player.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FishGame.resume();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            FishGame.pause();
            finish();
            return true;
        }
        return false;
    }


}
