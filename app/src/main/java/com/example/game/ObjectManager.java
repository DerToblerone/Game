package com.example.game;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ObjectManager {

    private List<GameObject> objList;
    private Sprite errorSprite;
    private float x_player;
    private float y_player;

    public ObjectManager(Sprite error){

        objList = new ArrayList<>();
        errorSprite = error;
    }

    public void addObject(String type, String name, float x, float y, Sprite image){
        if (type == "seeker"){
            objList.add(new seekerObject(name,x,y,image));
        }
        else{
            objList.add(new GameObject(name,x,y,image));
        }
    }

    public void addBackground(String name, int r, int g, int b){
        objList.add(new BackgroundObject(name,r,g,b,errorSprite));
    }


    public float[] getPlayerPos (){
        Iterator<GameObject> objectIterator = objList.iterator();
        while(objectIterator.hasNext()){
            GameObject tempObj = objectIterator.next();
            if (tempObj.objName == "player"){
                float[] tmp ={0,0};
                tmp[0] = tempObj.x;
                tmp[1] = tempObj.y;;
                x_player = tmp[0];
                y_player = tmp[1];
                return tmp;
            }
        }
        float[] errArray = {0,0};
        return errArray;
    }

    public void updateId (String index, float x, float y){
        Iterator<GameObject> objectIterator = objList.iterator();
        while(objectIterator.hasNext()){
            GameObject tempObj = objectIterator.next();
            if (tempObj.objName == index){
                tempObj.setCoordinates(x,y);
                break;
            }
        }
    }


    public void update(){
        getPlayerPos();
        Iterator<GameObject> objectIterator = objList.iterator();
        while(objectIterator.hasNext()){
            GameObject tempObj = objectIterator.next();
            if (tempObj.objType == "seeker"){
                tempObj.setPlayerPos(x_player, y_player);
            }
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

    public void updateType(String type, float x, float y){
        float[] target = getPlayerPos();
        Iterator<GameObject> objectIterator = objList.iterator();
        while(objectIterator.hasNext()){
            GameObject tempObj = objectIterator.next();
            if (tempObj.objType == type){
                if(type == "seeker"){

                    tempObj.setCoordinates(target[0], target[1]);
                }
                else {
                    tempObj.setCoordinates(x, y);
                }
            }
        }
    }
}
