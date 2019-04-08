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

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    private Bitmap background;
    private Student student;
    private View view;
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    private Random rand = new Random();
    private Paint scorePaint = new Paint();
    private int score = 0;
    private int canvasWidth, canvasHeight, eventY;
    private final static int LIENAR_ACCELERATION = 21, CONSTANT_ACCELERATION = 20, VELOCITY = 1, MAX_GRADE_IN_SCREEN = 10;
    private int Acceleration_Mod = LIENAR_ACCELERATION;
    private boolean eventTouch;
    private final static double MIDDLE_SCREEN = 2.5;

    public GameView(Context context) {
        super(context);

        student = new Student(this);
        view = this;
        background = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
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
//                    System.out.println("-----TEST POUR Y INFERIEUR A 0------");
//                    System.out.println("Student Y :"+student.getY());
            } if((canvasHeight/MIDDLE_SCREEN)-eventY <=0 && InMap(canvas,student)){
                //if(InMap(canvas,student)) {
                    goDown();
//                    System.out.println("-----TEST POUR Y SUPERIEUR A "+canvasHeight+"------");
//                    System.out.println("Student Y : "+student.getY()+" Height : "+student.getHeight());
            }
        }if(student.getY() == -1){
            student.setY((int) (canvasHeight/MIDDLE_SCREEN));
//            grade.setX(canvasHeight+ grade.getHeight());
//            grade.setY((int) (canvasHeight/MIDDLE_SCREEN));
//            CreateGrades(view);
        }

        CreateGrades(view);
        canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(student.getBitmap(),0,student.getY(),null);
        DrawListGrades(canvas);
        canvas.drawText("Score : "+score,20,60, scorePaint);
        MoveListGrades();
        DetectAllColission(student,grades);

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

    private void CreateGrades(View view){

        for(int i = 0;i<MAX_GRADE_IN_SCREEN;i++){
            if(grades.size() <MAX_GRADE_IN_SCREEN){
                CreateGrade(grades, view);
            }
        }

    }

    private  void CreateGrade(ArrayList<Grade> grades, View view){
        Grade grade = new Grade(view);
        grade.setX(canvasHeight+grade.getHeight());
        grade.setY(rand.nextInt(canvasHeight-grade.getHeight())+grade.getHeight());
        grades.add(grade);
    }

    private  void MoveListGrades(){
        for(int i =0; i<grades.size();i++){
            grades.get(i).move();
        }
    }

    private void DrawListGrades(Canvas canvas){
        for(int i =0; i<grades.size();i++){
            canvas.drawBitmap(grades.get(i).getBitmap(), grades.get(i).getX(), grades.get(i).getY(),null);
        }
    }

    private boolean Collision(Student student, Grade grade){
        return student.getX()<grade.getX()+grade.getWidth() && (student.getX()+grade.getWidth())>grade.getX() && student.getY()<grade.getY()+grade.getHeight() && student.getY()+grade.getHeight()>grade.getY();
    }

    private void DetectAllColission(Student student,ArrayList<Grade> grades){
        for(int i = 0;i<grades.size();i++){
            if(Collision(student,grades.get(i))){
                score = score + grades.get(i).getScore();
                grades.remove(grades.get(i));
            }
        }
    }
}
