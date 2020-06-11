package com.example.game;

import android.graphics.Canvas;
import android.graphics.Xfermode;
import android.graphics.Paint;

public class GameObject {
    private Sprite objSprite;
    private int x,y;
    private Paint paint;
    private float x_target;
    private float y_target;


    public String objName;



    public GameObject(String name, int X, int Y, Sprite image){
        objSprite = image;
        objName = name;
        x = X;
        y = Y;
        x_target = 0;
        y_target = 0;
    }

    public void update(){
        //code für logik
        if (Math.abs(x-x_target + y - y_target) > 0.2){
            float dir_x = x_target - x;
            float dir_y = y_target - y;
            x = (int)(x + 0.01*dir_x);
            y = (int)(y + 0.01*dir_y);
        }

        //am ende sprite update
        objSprite.update(x,y);
    }

    public void draw(Canvas canvas){
        objSprite.draw(canvas);
    }

    public void setCoordinates(float _x, float _y){
        x_target = (int)_x;
        y_target = (int)_y;
    }
}
