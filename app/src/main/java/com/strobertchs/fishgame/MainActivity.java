package com.strobertchs.fishgame;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        FishGame = new GameActivity(this, screenWidth, screenHeight);
        setContentView(FishGame);

    }


}
