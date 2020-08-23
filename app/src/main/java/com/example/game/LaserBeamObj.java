package com.example.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class LaserBeamObj extends GameObject {
    private Paint paint;
    private float x_player;
    private float y_player;

    private int damageValue;

    public LaserBeamObj(String name, float X, float Y, Sprite image){
        super(name,X, Y, image);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(0);
        objType = "laser";
        damageValue = 0;

    }

    @Override
    public void draw(Canvas canvas){
        objSprite.draw(canvas, true);


    }

    @Override
    public int getDamageVal(){
        return damageValue;
    }

    @Override
    public void update(){
         if (y > 1.1){
             exist = false;
         }

         y = y + 0.017f;
        if (Math.abs(x- x_player) < 0.03f && Math.abs(y-y_player) < 0.06f){
            damageValue = 10;
            exist = false;

        }
         objSprite.update(x,y);

    }
    public void setPlayerPos(float x_p, float y_p){
        x_player = x_p;
        y_player = y_p;

    }
}
