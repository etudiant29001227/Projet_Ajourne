package com.example.projet_ajourn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.util.Random;

public class Grade implements DrawableObject {

    private Bitmap number;
    private int[] ListBitmap ={R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero
            ,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero,R.drawable.zero};
    private Random rand = new Random();
    private final static int MAX_GRADE = 21, LOW_RATING = 10, MEDIUM_RATING = 15;
    private int numberX,numberY,speed,grade;

    public Grade(View view){
        grade = rand.nextInt(MAX_GRADE);

        number = BitmapFactory.decodeResource(view.getResources(),ListBitmap[grade]);

        if(grade < LOW_RATING){
            speed = 10;
        }if(grade>LOW_RATING && grade<MEDIUM_RATING){
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
        return grade;
    }
}
