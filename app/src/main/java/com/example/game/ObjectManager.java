package com.example.game;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ObjectManager {

    private List<GameObject> objList;
    private Sprite errorSprite;

    public ObjectManager(Sprite error){

        objList = new ArrayList<>();
        errorSprite = error;
    }

    public void addObject(String name, int x, int y, Sprite image){
        objList.add(new GameObject(name,x,y,image));
    }

    public void addBackground(String name, int r, int g, int b){
        objList.add(new BackgroundObject(name,r,g,b,errorSprite));
    }

    public void updateId (String index, float x, float y){
        Iterator<GameObject> objectIterator = objList.iterator();
        while(objectIterator.hasNext()){
            GameObject tempObj = objectIterator.next();
            if (tempObj.objName == index){
                tempObj.setCoordinates(x,y);
            }
        }
    }


    public void update(){
        Iterator<GameObject> objectIterator = objList.iterator();
        while(objectIterator.hasNext()){
            GameObject tempObj = objectIterator.next();
            tempObj.update();
        }
    }

    public void draw(Canvas canvas){
        Iterator<GameObject> objectIterator = objList.iterator();
        while(objectIterator.hasNext()){
            GameObject tempObj = objectIterator.next();
            tempObj.draw(canvas);
        }
    }
}
