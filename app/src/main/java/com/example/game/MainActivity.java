package com.example.game;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ContentView;


public class MainActivity extends Activity {

    private GameView gameView;
    private boolean activityActive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityActive = true;
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //setContentView(R.layout.activity_main);
        gameView = new GameView(this);
        setContentView(gameView);//custom View klasse wird ausgewählt

        hideSystemUI();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

     @Override
     protected void onPause(){
        super.onPause();
        gameView.pauseGame();
        activityActive = false;
     }

     @Override
     protected void onResume(){
        super.onResume();
        if(!activityActive){
           gameView.resumeGame();
            activityActive = true;
            hideSystemUI();
        }

     }


    public void hideSystemUI (){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }



}