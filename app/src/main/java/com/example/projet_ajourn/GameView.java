package com.example.projet_ajourn;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;

public class GameView extends View {

    private Bitmap background;
    private Student student;
    private Number number;
    private Paint score = new Paint();
    private int canvasWidth, canvasHeight, eventY;
    private final static int LIENAR_ACCELERATION = 21, CONSTANT_ACCELERATION = 20, VELOCITY = 1;
    private int Acceleration_Mod = LIENAR_ACCELERATION;
    private boolean eventTouch;
    private final static double MIDDLE_SCREEN = 2.5;

    public GameView(Context context) {
        super(context);

        student = new Student(this);
        number = new Number(this);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        score.setColor(Color.WHITE);
        score.setTextSize(70);
        score.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        if(eventTouch){
            if((canvasHeight/MIDDLE_SCREEN)-eventY>=0 && InMap(canvas,student)){
                //if(InMap(canvas,student)){
                    goUp();
                    System.out.println("-----TEST POUR Y INFERIEUR A 0------");
                    System.out.println("Student Y :"+student.getY());
            } if((canvasHeight/MIDDLE_SCREEN)-eventY <=0 && InMap(canvas,student)){
                //if(InMap(canvas,student)) {
                    goDown();
                    System.out.println("-----TEST POUR Y SUPERIEUR A "+canvasHeight+"------");
                    System.out.println("Student Y : "+student.getY()+" Height : "+student.getHeight());
            }
        }if(student.getY() == -1){
            student.setY((int) (canvasHeight/MIDDLE_SCREEN));
            number.setX(canvasHeight+number.getHeight());
            number.setY((int) (canvasHeight/MIDDLE_SCREEN));
        }

        canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(student.getBitmap(),0,student.getY(),null);
        canvas.drawBitmap(number.getBitmap(),number.getX(),number.getY(),null);
        canvas.drawText("Score : ",20,60,score);
        number.move();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            eventTouch = true;
            eventY = (int)event.getY();
        }if(event.getAction() == MotionEvent.ACTION_UP){
            Stop();
            eventTouch = false;
        }
        return true;
    }

    private boolean InMap(Canvas canvas,DrawableObject object){
        if(object.getY()+object.getHeight()<=canvas.getHeight() && object.getY()>=0){
            return true;
        }
        return false ;
    }

    private void goUp(){
        switch (Acceleration_Mod) {
            case CONSTANT_ACCELERATION:
                student.setSpeed(-(Math.abs(student.getSpeed())));
                break;
            case LIENAR_ACCELERATION:
                student.setSpeed(student.getSpeed()-VELOCITY);
        }
            student.setY(Math.max(student.getY() + student.getSpeed(), 0));

    }

    private void goDown(){
        switch (Acceleration_Mod) {
            case CONSTANT_ACCELERATION:
                student.setSpeed(Math.abs(student.getSpeed()));
                break;
            case LIENAR_ACCELERATION:
                student.setSpeed(student.getSpeed()+VELOCITY);
        }

        student.setY(Math.min(student.getY() + student.getSpeed(),canvasHeight-student.getHeight()));
    }
    private void Stop(){
        if(Acceleration_Mod == LIENAR_ACCELERATION){
            student.setSpeed(0);
        }
    }
}
