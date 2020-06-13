package com.example.game;

import android.graphics.Canvas;

public class seekerObject extends GameObject {
    private float x_dir;
    private float y_dir;
    private float x_target;
    private float y_target;

    public seekerObject(String name, int X, int Y, Sprite image){
        super(name,X,Y,image);
        x_dir = 0;
        y_dir = 0;
        setTarget(500,1000);
        objType = "seeker";

    }

    public void setTarget(int X, int Y){
        x_target = X;
        y_target = Y;

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
        if (y_delta > 200) {
            x_dir = 15 * x_delta / total;
            y_dir = 15 * y_delta / total;
        }
        x = (int)(x + x_dir);
        y = (int)(y + y_dir);

        objSprite.update(x,y);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void setCoordinates(float _x, float _y) {
        x_target = _x;
        y_target = _y;
    }
}
