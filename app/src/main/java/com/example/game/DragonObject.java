package com.example.game;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class DragonObject extends GameObject {
    private float speed;
    private float base_x;

    private float x_player;
    private float y_player;

    private Sprite bodySprite;

    private float phaseOffset;

    private int damageValue;
    private Random rng;


    public DragonObject(String name, float X, float Y, Sprite image, Sprite imageBody){
        super(name, X, Y, image);
        objType = "dragon";
        exist = true;
        speed = 0.003f;
        base_x = X;
        damageValue = 0;
        bodySprite = imageBody;
        rng = new Random();
        phaseOffset = rng.nextFloat();

    };

    @Override
    public void setPlayerPos(float x_p, float y_p){
        x_player =x_p;
        y_player = y_p;


    }

    @Override
    public void update(){
        y += speed;
        x = base_x + 0.2f*(float)(Math.sin(15*y + phaseOffset));

        if (Math.abs(x- x_player) + Math.abs(y-y_player) < 0.05){
            damageValue = 10;
            exist = false;
        }

        if (y > 1.4){
            exist = false;
        }
        objSprite.update(x,y);
    }

    public void draw(Canvas canvas){
        float new_y;
        float ang;
        for(int i = 15; i > 0; i--){
            new_y = y - i*0.012f;
            ang = (float) (18*Math.sin(15*new_y));
            bodySprite.update( base_x + 0.2f*(float)(Math.sin(15*new_y + phaseOffset)), new_y);
            bodySprite.rotate(ang);
            bodySprite.draw(canvas,true);
        }
        objSprite.draw(canvas,true);
    }
}
