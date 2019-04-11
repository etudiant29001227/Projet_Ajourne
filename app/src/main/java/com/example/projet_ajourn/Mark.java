package com.example.projet_ajourn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.util.Random;

public class Mark implements DrawableObject {

    private Bitmap number;
    private int[] ListBitmap ={R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero
            ,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero};
    private Random rand = new Random();
    private final static int MAX_MARK = 21, LOW_RATING = 10, MEDIUM_RATING = 15;
    private int numberX,numberY,speed, mark;

    public Mark(View view){
        mark = rand.nextInt(MAX_MARK);

        number = BitmapFactory.decodeResource(view.getResources(),ListBitmap[mark]);

        if(mark < LOW_RATING){
            speed = 10;
        }if(mark >LOW_RATING && mark <MEDIUM_RATING){
            speed = 15;
        }else{
            speed = 25;
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
}
