package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.graphics.Paint;

import java.util.Random;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private ObjectManager objManager;
    private int screenWidth;
    private int screenHeight;
    private int count; //framecounter
    private Random rng;



    private DisplayMetrics metricsDisp;
    private BitmapFactory.Options optionsBmp;

    //Sprite resources
    private Sprite seekerSprite;
    private Sprite laserSprite;


    private Sprite healthOverlaySprite;
    private Sprite healthBarSprite;



    private Paint paint;
    private Paint paintUI;

    float newX, newY;
    float healthPercent;

    private boolean gameOver;

    //menu rects
    private  Rect retryButton;


    private OnTouchListener eventListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            if(gameOver){
                if (retryButton.left < x && x < retryButton.right && retryButton.top > y && y > retryButton.bottom){
                    gameOver = false;
                    initGame();
                }
                return false;
            }
            newX = x/screenWidth;
            newY = y/screenHeight;
            if (newX <  0.2){
            newX = 0;
            }
            if(newX > 0.8){
                newX = 1;
            }
            objManager.updateId("player",newX,newY);
            //objManager.updateType("seeker", 0, 0);
            return false;
        }
    };

    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this); //Programmablauf wird hier abgewickelt
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);

        paintUI = new Paint();
        paintUI.setColor(Color.WHITE);
        paintUI.setTextSize(50);

        rng = new Random();

        setFocusable(true);
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        this.setOnTouchListener(eventListener);

        metricsDisp = getResources().getDisplayMetrics();
        optionsBmp = new BitmapFactory.Options();
        optionsBmp.inDensity = 200;
        optionsBmp.inScaled = true;
        optionsBmp.inScreenDensity = metricsDisp.densityDpi;



        seekerSprite = new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.newseeker,optionsBmp), 0.05f, 0.08f);
        laserSprite = new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.sword,optionsBmp), 0.03f, 0.12f);
        healthOverlaySprite = new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.overlayneu,optionsBmp), 1.0f, 0.2f);
        healthBarSprite = new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.healthbarneu,optionsBmp), 1.0f, 0.075f);

        //menu items:
        retryButton = new Rect(0,(int)(3*screenHeight/5),screenWidth,(int)(2*screenHeight/5));


        initGame();
    }

    public void initGame(){
        objManager = new ObjectManager(new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.test, optionsBmp),0.05f,0.05f));


        objManager.addBackground("floor",255,255, 255, new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.papier, optionsBmp),1,1));
        objManager.addObject("player","player", 1.0f/2.0f,9.0f/10.0f , new Sprite(BitmapFactory.decodeResource(getResources(),R.drawable.playersprite, optionsBmp),0.15f, 0.1f));

        newX = 0;
        newY = 0;
        //healthOverlaySprite.update(0.5f, 0.07f);
        healthOverlaySprite.update(0.5f, 0.07f);


        healthPercent = 1.0f;


        gameOver = false;

        count = 0;
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
        thread.setRunning(true);
        thread.start();
    }

    public void update(){
        if (gameOver){

            return;
        }
        if (healthPercent < 0){
            gameOver = true;
            return;
        }

        if(rng.nextInt(50) < 1){
            spawnEnemey(1.0f/2.0f, 0, 2);
        }

        if (count%40 == 0){
            objManager.updateType("seeker",0,0);
        }
        objManager.update();
        healthPercent = objManager.getHealthPercent();
        count++;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null){

            objManager.draw(canvas);
            drawUI(canvas);
        }
    }

    public void drawUI(Canvas canvas){
        //paint.setColor(Color.GREEN);
        //canvas.drawRect(0,0,screenWidth*objManager.getHealthPercent(),screenHeight/12, paint);
        paint.setColor(Color.WHITE);
        healthBarSprite.update(-0.5f + objManager.getHealthPercent(),0.037f);
        healthBarSprite.draw(canvas,true);
        healthOverlaySprite.draw(canvas, true);
        Rect retryButton = new Rect(0,(int)(3*screenHeight/5),screenWidth,(int)(2*screenHeight/5));
        canvas.drawText("Score: " + objManager.getScore(), 0,screenHeight/22,paintUI);


        if (gameOver){
            paint.setColor(Color.argb(180,0,0,0));
            paint.setTextSize(screenHeight/9);
            canvas.drawRect(retryButton, paint);

            paint.setColor(Color.argb(255,125,90,130));

            canvas.drawText("Game Over", 0,screenHeight/2,paint);

            paint.setTextSize(screenHeight/18);
            canvas.drawText("git gud and try again", 0,3*screenHeight/5,paint);

        }
        //        paint.setTextSize(50);
        //canvas.drawText(Double.toString(thread.fps()),10,50,paint);//bei canvas muss mit pixeln gearbeitet werden
        //canvas.drawText(Double.toString(objManager.getHealthPercent()),10,110,paint);//bei canvas muss mit pixeln gearbeitet werden

    }

    public void spawnEnemey(float x, float y, int n){
        float rng_x;
        for (int i = 0; i <= n; i++){
            rng_x =rng.nextInt(100)/100.0f;

            if (rng.nextBoolean()) {
                Sprite tmp = seekerSprite.clone();
                objManager.addObject("seeker", "redRocket", rng_x, y, tmp);
            }
            else{
                Sprite tmp = laserSprite.clone();
                objManager.addObject("laser", "greenBeam", rng_x, y, tmp);
            }
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
