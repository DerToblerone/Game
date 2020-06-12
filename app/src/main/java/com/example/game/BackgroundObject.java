package com.example.game;

import android.graphics.Canvas;

public class BackgroundObject extends GameObject {
    private int r;
    private int g;
    private int b;


    public BackgroundObject(String name, int r_,int g_, int b_,Sprite image) {
        super(name,0,0,image);
        r = r_;
        g = g_;
        b = b_;

    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawARGB(100, r, g,b );
    }

}
