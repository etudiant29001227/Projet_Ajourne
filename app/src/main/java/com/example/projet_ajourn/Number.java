package com.example.projet_ajourn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Number implements DrawableObject {

    private Bitmap number;
    private int numberX,numberY,speed =35;

    public Number(View view){
        number = BitmapFactory.decodeResource(view.getResources(),R.drawable.zero);
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

    public void setX(int X){
        numberX = X;
    }

    public void setY(int Y){
        numberY = Y;
    }
    public void move(){
        numberX = numberX - speed;
    }
}
