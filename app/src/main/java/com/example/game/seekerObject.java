package com.example.game;

import android.graphics.Canvas;

public class seekerObject extends GameObject {
    private float x_dir;
    private float y_dir;
    private float x_target;
    private float y_target;
    private float x_player;
    private float y_player;

    public seekerObject(String name, float X, float Y, Sprite image){
        super(name,X,Y,image);
        x_dir = 0;
        y_dir = 0;
        setTarget(500,1000);
        objType = "seeker";

    }

    public void setTarget(float X, float Y){
        x_target = X;
        y_target = Y;
        float x_delta = x_target-x;
        float y_delta = y_target -y;

        float ang = (float)Math.atan(x_delta/y_delta);
        ang = (float)(ang*(180/Math.PI));
        objSprite.rotate(ang);
    }

    @Override
    public void update() {
        float x_delta = x_target-x;
        float y_delta = y_target -y;
        float total = Math.abs(x_delta) + Math.abs(y_delta);
        if (y_delta <- 200){
            return;
        }
        float norm = (float)Math.sqrt(x_delta*x_delta + y_delta*y_delta);
        if (y_delta > 0.15) {
            x_dir =  (float) 0.01*x_delta / total;
            y_dir =  (float) 0.01*y_delta / total;
            float ang = (float)Math.atan(-x_delta/y_delta);
            ang = (float)(ang*(180/Math.PI));
            objSprite.rotate(ang);
        }
        x += x_dir;
        y += y_dir;

        objSprite.update(x,y);
    }

    @Override
    public void draw(Canvas canvas) {
        objSprite.draw(canvas,true);
    }

    @Override
    public void setCoordinates(float _x, float _y) {
        x_target = _x;
        y_target = _y;
    }

    @Override
    public void setPlayerPos(float x_p, float y_p){
        x_player =x_p;
        y_player = y_p;

    }
}
