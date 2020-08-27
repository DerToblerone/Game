package com.example.game;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ParticleEffect {
    private float x;
    private float y;
    private int type;
    private int counter;

    private int particleCount;

    private List<Particle> particleList;

    private Paint particleColor;

    public ParticleEffect(float _x, float _y, int _t, int pCount){
        x = _x;
        y = _y;
        type = _t;

        counter = 10;

        particleCount = pCount;


        particleList = new ArrayList<>();

        particleColor = new Paint();
        particleColor.setStrokeWidth(3f);
        particleColor.setARGB(165,100,20,255);



        float tmp = 0;
        for(int i = 0; i < particleCount; i++){

            tmp = i/(pCount*1.0f);

            tmp = (float)(tmp*Math.PI*2);


            particleList.add(new Particle(x,y-0.03f,0.02f*(float)Math.sin(tmp),0.02f*(float)Math.cos(tmp),1));
        }

    }

    public boolean update(){
        if (counter >= 0){
            counter--;
            particleColor.setARGB(45 + counter*10,100,20,255);
            Iterator<Particle> pIterator = particleList.iterator();
            while(pIterator.hasNext()) {
                Particle tmP = pIterator.next();
                tmP.update();
            }

        }
        else {
            particleList.clear();
            return true;
        }

        return false;

    }

    public void draw(Canvas canvas){
        Iterator<Particle> pIterator = particleList.iterator();
        while(pIterator.hasNext()) {
            Particle tmP = pIterator.next();
            tmP.draw(canvas, particleColor);
        }
    }

}
