package com.example.game;

import android.graphics.Canvas;
import android.graphics.Xfermode;

public class GameObject {
    private Sprite objSprite;
    private int x,y;
    public String objName;


    public GameObject(String name, int X, int Y, Sprite image){
        objSprite = image;
        objName = name;
        x = X;
        y = Y;
    }

    public void update(){
        //code für logik


        //am ende sprite update
        objSprite.update(x,y);
    }

    public void draw(Canvas canvas){
        objSprite.draw(canvas);
    }

    public void setCoordinates(float _x, float _y){
        x = (int)_x;
        y = (int)_y;
    }
}
