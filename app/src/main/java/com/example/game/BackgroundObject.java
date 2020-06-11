package com.example.game;

import android.graphics.Canvas;

public class BackgroundObject extends GameObject {


    public BackgroundObject(String name, Sprite image) {
        super(name,0,0,image);


    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Canvas canvas){
        objSprite.draw(canvas, true);
    }

}
