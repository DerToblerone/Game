package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

public class Sprite {
    private Bitmap image;
    private Bitmap imageFlipped;

    private int x,y;
    protected int width, height;


    private int screenWidth;
    private int screenHeight;

    private int x_offset;
    private int y_offset;
    private boolean show;

    public Sprite(Bitmap bmp){
        image = bmp;
        flip(bmp);
        x = 0;
        y = 0;
        width = image.getWidth();
        height = image.getHeight();
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        x_offset = (int)width/2;
        y_offset = (int)height/2;
        show = true;

    }

    public void draw(Canvas canvas, boolean direction){
        if(show) {
            canvas.drawBitmap(direction ? image : imageFlipped, x-x_offset, y-y_offset, null);
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

    public void flip(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap src = image;
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        imageFlipped = dst;
    }
}
