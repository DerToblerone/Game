package com.example.game;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ObjectManager {

    private List<GameObject> objList;
    private List<Integer> delIndexList;
    private Sprite errorSprite;
    private float x_player;
    private float y_player;
    private int playerHealth;
    private int maxPlayerHealth;
    private int gameScore;
    private int playerInvincible;

    private boolean kChanged;

    public ObjectManager(Sprite error){

        objList = new ArrayList<>();
        delIndexList = new ArrayList<>();
        errorSprite = error;
        playerHealth = 100;
        maxPlayerHealth = 100;
        gameScore = 0;
        playerInvincible = 0;
    }

    public void addObject(String type, String name, float x, float y, Sprite image){
        if (type == "seeker"){
            objList.add(new seekerObject(name,x,y,image));
        }
        else if (type == "laser"){
            objList.add(new LaserBeamObj(name,x,y,image));


        }
        else{
            objList.add(new GameObject(name,x,y,image));
        }
    }

    public void addBackground(String name, int r, int g, int b, Sprite backgroundSprite){
        objList.add(new BackgroundObject(name,r,g,b,backgroundSprite));
    }
    public int getScore(){
        return gameScore;
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
        int i = 0;
        int[] k = new int[objList.size()];
        kChanged = false;
        if(playerInvincible > 0){
            playerInvincible--;
        }
        for (GameObject tempObj : objList) {
            if (tempObj.objType.equals("seeker") | tempObj.objType.equals("laser")) {
                tempObj.setPlayerPos(x_player, y_player);
                if (!tempObj.exist) {
                    gameScore++;
                    k[i] = i;
                    kChanged = true;
                    //delIndexList.add(i);
                }/*else if(tempObj.objType =="laser") {
                    tempObj.setPlayerPos(x_player, y_player);
                    if (tempObj.exist == false) {
                        k[i] = i;
                        kChanged = true;
                        //delIndexList.add(i);
                    }
                }*/
            }
            tempObj.update();

            i++;
        }
        if( kChanged){//delIndexList.size() > 0){
        cleanupObjList(k);}
    }
    /*
    public static void bytefill(byte[] array, byte value) {
        int len = array.length;

        if (len > 0){
            array[0] = value;
        }

        //Value of i will be [1, 2, 4, 8, 16, 32, ..., len]
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, 0, array, i, ((len - i) < i) ? (len - i) : i);
        }
    }
    */
    private void cleanupObjList(int[] k){
       /*
        Iterator<Integer> indexIterator = delIndexList.iterator();
        int index;
        while(indexIterator.hasNext()) {
            index = indexIterator.next();
            objList.remove(index);
        }
        delIndexList.clear();
        index = 0;

        */
       for (int i = k.length -1 ; i > 0; i--){
           if (k[i] != 0){
               GameObject tmp = objList.get(i);
               int control = tmp.getDamageVal();
               if (control != 0){
                   if (playerInvincible == 0) {
                       playerHealth -= control;
                       playerInvincible = 20;
                   }
               }
               objList.remove(k[i]);
           }

       }

    }

    public void draw(Canvas canvas){
        Iterator<GameObject> objectIterator = objList.iterator();
        while(objectIterator.hasNext()){
            GameObject tempObj = objectIterator.next();
            if(playerInvincible >0 && tempObj.objType == "player"){
                if (playerInvincible%3 == 0){
                    tempObj.draw(canvas);
                }
            }
            else {
                tempObj.draw(canvas);
            }
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

    public float getHealthPercent(){
        return playerHealth*1.0f/maxPlayerHealth;
    }
}
