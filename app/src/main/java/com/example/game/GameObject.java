package com.example.game;

import android.graphics.Canvas;
import android.graphics.Xfermode;
import android.graphics.Paint;

public class GameObject {
    public Sprite objSprite;
    public float x,y;
    public String objType;


    private Paint paint;
    private float x_target;
    private float y_target;

    private float x_velocity;
    private float y_velocity;

    private float v_max;



    private float dir_x;
    private float dir_y;


    public boolean exist;
    public String objName;
    private int damageValue;



    public GameObject(String name, float X, float Y, Sprite image){
        objSprite = image;
        objName = name;
        x = X;
        y = Y;
        x_target = X;
        y_target = Y;

        dir_x = x_target - x;
        dir_y = y_target - y;

        exist = true;
        objType = "player";

        damageValue = 0;

        x_velocity = 0;
        y_velocity = 0;

        v_max = 2;

    }

    public void update(){
        //code für logik
        /*
        float distance = x-x_target + y - y_target;
        float absDistance = Math.abs(distance);
        if (absDistance > 0.05){
            dir_x = x_target - x;
            if(dir_x < 0.005 && dir_x > -0.005){
                dir_x = 0;
            }
            //dir_y = y_target - y;
            x = (float)(x + 0.05*dir_x + signum(dir_x)/60);
            y = (float)(y + 0.05*dir_y);

        }
        */
        x = (float)(x + x_velocity/60);//x + 0.05*dir_x + signum(dir_x)/60);
        //y = (float)(y + y_velocity);//y + 0.05*dir_y);
        if (x_velocity > 0.01){
            dir_x = 1;
        }
        else if(x_velocity < -0.01){
            dir_x = -1;
        }
        if (x>1){
            x = 1;
        }
        else if(x < 0){
            x = 0;
        }

        x_velocity *= 0.9;
        y_velocity *= 0.9;
        //am ende sprite update

        objSprite.update(x,y);
    }

    public int getDamageVal(){  return damageValue;    }
    public void draw(Canvas canvas){
        objSprite.draw(canvas,dir_x > 0 );
    }

    public void setCoordinates(float _x, float _y){
        x_target = _x;
        y_target = _y;
        x_velocity = _x/10;
        y_velocity = _y/10;

        if(x_velocity > v_max){
            x_velocity = v_max;
        }
        else if(x_velocity < -v_max){
            x_velocity = -v_max;
        }
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

    public void setPlayerPos(float x, float y){

    }

}
