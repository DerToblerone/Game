package com.example.game;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Particle {

    private float oldX;
    private float posX;
    private float velX;

    private float oldY;
    private float posY;
    private float velY;

    private int particleMode;

    public Particle(float px, float py, float vx, float vy, int pmode){
        posX = px;
        posY = py;

        velX = vx;
        velY = vy;

        oldX = posX;
        oldY = posY;

        particleMode = pmode;
    }

    public void update(){
        oldY = posY;
        oldX = posX;
        posX += velX;
        posY += velY;

    }

    public void draw(Canvas canvas, Paint paint){

        if(particleMode == 0){
            canvas.drawPoint(posX,posY,paint);
        }
        else if(particleMode == 1){
            canvas.drawLine(oldX*canvas.getWidth(),oldY*canvas.getHeight(),posX*canvas.getWidth(),posY*canvas.getHeight(),paint);
        }
    }

}
