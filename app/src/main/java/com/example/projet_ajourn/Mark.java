package com.example.projet_ajourn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.util.Random;

public class Mark implements DrawableObject {

    private Bitmap number;
    private int[] ListBitmap ={R.drawable.mark_0,R.drawable.mark_1,R.drawable.mark_2,R.drawable.mark_3,R.drawable.mark_4,R.drawable.mark_5,R.drawable.mark_6,R.drawable.mark_7,R.drawable.mark_8,R.drawable.mark_9
            ,R.drawable.mark_10,R.drawable.mark_11,R.drawable.mark_12,R.drawable.mark_13,R.drawable.mark_14,R.drawable.mark_15,R.drawable.mark_16,R.drawable.mark_17,R.drawable.mark_18,R.drawable.mark_19,R.drawable.mark_20};
    private Random rand = new Random();
    private final static int MAX_MARK = 21, LOW_RATING = 10, MEDIUM_RATING = 15;
    private int numberX,numberY,speed, mark;

    public Mark(View view){
        mark = rand.nextInt(MAX_MARK);

        number = BitmapFactory.decodeResource(view.getResources(),ListBitmap[mark]);

        if(mark < LOW_RATING){
            speed = 5;
        }if(mark >LOW_RATING && mark <MEDIUM_RATING){
            speed = 10;
        }else{
            speed = 15;
        }

    }

    public Bitmap getBitmap(){
        return number;
    }
    @Override
    public int getX() {
        return numberX;
    }

    @Override
    public int getY() {
        return numberY;
    }

    @Override
    public int getHeight() {
        return number.getHeight();
    }

    public int getWidth(){return number.getWidth();}

    public void setX(int X){
        numberX = X;
    }

    public void setY(int Y){
        numberY = Y;
    }

    public void move(){
        numberX = numberX - speed;
    }

    public int getScore(){
        return mark;
    }

    @Override
    public int getMaxHeight() {
        return 0;
    }
}
