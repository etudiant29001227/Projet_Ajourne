package com.example.projet_ajourn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class Student implements DrawableObject{
    private static int MAX_SPEED = 50;
    private Bitmap student;
    private int[] walkDownBitmap ={R.drawable.walk_down_0,R.drawable.walk_down_1,R.drawable.walk_down_2,R.drawable.walk_down_3};
    private int[] walkUpBitmap ={R.drawable.walk_up_0,R.drawable.walk_up_1,R.drawable.walk_up_2,R.drawable.walk_up_3};
    private int studentY = -1,studentX, speed = 25,walk_down = 0, walk_up_status = 0, change_animation_cooldown = 0,max_Height;
    private View view;

    public Student( View view){
        this.view = view;
        student = BitmapFactory.decodeResource(view.getResources(),R.drawable.repose);
        max_Height = student.getHeight();

        for(int i = 0;i<walkDownBitmap.length;i++){
            int height = BitmapFactory.decodeResource(view.getResources(),walkUpBitmap[i]).getHeight();
            if(max_Height<height){
                max_Height = height;
            }
            height = BitmapFactory.decodeResource(view.getResources(),walkDownBitmap[i]).getHeight();
            if(max_Height<height){
                max_Height = height;
            }
        }
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

    public int getWidth(){
        return student.getWidth();
    }

    public int getHeight(){
        return student.getHeight();
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

    public int getMaxHeight(){
        return max_Height;
    }

    public void walkUpAnimation(){
        student = BitmapFactory.decodeResource(view.getResources(),walkUpBitmap[walk_up_status]);
        if(moveTonextAnimation()) {
            if (walk_up_status < walkUpBitmap.length - 1) {
                walk_up_status++;
            } else {
                walk_up_status = 0;
            }
        }

    }

    public void walkDownAnimation(){
        student = BitmapFactory.decodeResource(view.getResources(),walkDownBitmap[walk_down]);
        if(moveTonextAnimation()) {
                if (walk_down < walkDownBitmap.length - 1) {
                    walk_down++;
                } else {
                    walk_down = 0;
                }
        }
    }

    public void reposeStudent(){
        student = BitmapFactory.decodeResource(view.getResources(),R.drawable.repose);
    }

    public void setSpeed(int speed){
        if(Math.abs(speed)<MAX_SPEED) {
            this.speed = speed;
        }else{
            this.speed = MAX_SPEED*(speed/Math.abs(speed));
        }

    }

    public boolean moveTonextAnimation(){
        if(change_animation_cooldown>4){
            change_animation_cooldown = 0;
            return true;
        }
        change_animation_cooldown++;
        return false;
    }
}
