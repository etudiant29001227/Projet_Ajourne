package com.example.projet_ajourn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Student {
    private Bitmap student;
    private int studentY = 0,studentX, speed = 25;

    public Student( View view){
        student = BitmapFactory.decodeResource(view.getResources(),R.drawable.charcater8bit);
    }

    public int getX(){
        return studentX;
    }

    public int getY(){
        return studentY;
    }

    public int getSpeed(){
        return speed;
    }

    public Bitmap getBitmap(){
        return student;
    }

    public void setX(int X){
        studentX = X;
    }

    public void setY(int Y){
        studentY = Y ;
    }
}
