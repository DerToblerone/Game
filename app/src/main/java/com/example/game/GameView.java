package com.example.game;

import android.graphics.BitmapFactory;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private ObjectManager objManager;

    float newX, newY;


    private OnTouchListener eventListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            newX = event.getX();
            newY = event.getY();
            objManager.updateId("player",newX,newY);
            return false;
        }
    };

    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this); //Programmablauf wird hier abgewickelt
        setFocusable(true);

        this.setOnTouchListener(eventListener);

        objManager = new ObjectManager();

        newX = 0;
        newY = 0;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try {
                thread.setRunning(false);
                thread.join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        objManager.addObject("player", 0,0 , new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.test)));
        thread.setRunning(true);
        thread.start();
    }

    public void update(){
        objManager.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null){
            objManager.draw(canvas);

        }
    }


    /*
    class touchInputListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }
    */


}
