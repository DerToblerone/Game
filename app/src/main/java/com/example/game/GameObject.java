package com.example.game;

import android.graphics.Canvas;
import android.graphics.Xfermode;
import android.graphics.Paint;

public class GameObject {
    public Sprite objSprite;
    private int x,y;
    private Paint paint;
    private float x_target;
    private float y_target;
    private float x_offset;
    private float y_offset;

    private float dir_x;
    private float dir_y;

    int animIncrement;


    public String objName;



    public GameObject(String name, int X, int Y, Sprite image){
        objSprite = image;
        objName = name;
        x = X;
        y = Y;
        x_target = 0;
        y_target = 0;
        x_offset = 0;
        y_offset = 0;
        animIncrement= 1;

        dir_x = x_target - x;
        dir_y = y_target - y;


    }

    public void update(){
        //code für logik
        float distance = x-x_target + y - y_target;
        float absDistance = Math.abs(distance);
        if (absDistance > 0.2){
            dir_x = x_target - x;
            dir_y = y_target - y;
            x = (int)(x + 0.01*dir_x);
            y = (int)(y + 0.01*dir_y);
        }

        //am ende sprite update
        y_offset = y_offset + animIncrement*(1 + 0.1f*(40- y_offset))*absDistance*0.005f;
        if (y_offset > 40){
            animIncrement = -1;
        }
        else if (y_offset < 0){
            animIncrement = 1;
        }

        objSprite.update(x,(int)(y-y_offset));
    }

    public void draw(Canvas canvas){
        objSprite.draw(canvas,dir_x > 0 );
    }

    public void setCoordinates(float _x, float _y){
        x_target = (int)_x;
        y_target = (int)_y;
    }



}
