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
        x_target = X;
        y_target = Y;
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
            //dir_y = y_target - y;
            x = (int)(x + 0.05*dir_x + signum((dir_x)));
            y = (int)(y + 0.05*dir_y);
        }

        //am ende sprite update


        objSprite.update(x,y);
    }

    public void draw(Canvas canvas){
        objSprite.draw(canvas,dir_x > 0 );
    }

    public void setCoordinates(float _x, float _y){
        x_target = (int)_x;
        y_target = (int)_y;
    }



    public static float signum(float f) {
        return (f == 0.0f || isNaN(f)) ? f : copySign(1.0f, f);
    }

    public static boolean isNaN(float f) {
        return (f != f);
    }

    public static float copySign(float magnitude, float sign) {
        return rawCopySign(magnitude, (isNaN(sign) ? 1.0f : sign));
    }

    public static float rawCopySign(float magnitude, float sign) {
        return Float.intBitsToFloat((Float.floatToRawIntBits(sign)
                & (FloatConsts.SIGN_BIT_MASK)) //filtert die Zahlenwerte raus und lässt das signum stehen
                | (Float.floatToRawIntBits(magnitude)//schreibt in die exponenten bits sd. die bits von 1 stehen
                & (FloatConsts.EXP_BIT_MASK // filtert die anderen bits so dass der exponent 1 ist
                | FloatConsts.SIGNIF_BIT_MASK)));//nicht ganz sicher aber wahrscheinlich wird noch eine mantisse dazu gegeben?

    }

    static class FloatConsts {
        public static final int SIGN_BIT_MASK = -2147483648;
        public static final int EXP_BIT_MASK = 2139095040;
        public static final int SIGNIF_BIT_MASK = 8388607;
    }

}
