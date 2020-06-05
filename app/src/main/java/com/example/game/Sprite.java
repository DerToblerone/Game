package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
    private Bitmap image;

    private int x,y;
    protected int width, height;


    private int screenWidth;
    private int screenHeight;
    private boolean show;

    public Sprite(Bitmap bmp){
        image = bmp;
        x = 0;
        y = 0;
        width = image.getWidth();
        height = image.getHeight();
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        show = true;

    }

    public void draw(Canvas canvas){
        if(show) {
            canvas.drawBitmap(image, x, y, null);
        }
    }

    public void update(int X, int Y){ // gibt dem Sprite eine neue Position und entscheidet ob es gezeigt wird
        x = X;
        y = Y;

        if(x + width < 0 || x > screenWidth || y + height < 0 || y > screenHeight){
            show = false;
        }
        else{
            show = true;
        }

       /*
        if(x < 0 && y < 0){
            x = screenWidth/2;
            y = screenHeight/2;
        }
        else if(x> screenWidth - width || x < 0){
            x_vel *= -1;
        }
        else if(y> screenHeight - height || y < 0){
            y_vel *= -1;
        }

        x = x + x_vel;
        y = y + y_vel;

        */
    }
}
