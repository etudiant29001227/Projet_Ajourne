package com.example.projet_ajourn;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    private Bitmap background;
    private Student student;
    private View view;
    private ArrayList<Grade> grades = new ArrayList<>();
    private Random rand = new Random();
    private Paint scorePaint = new Paint(),timerPaint = new Paint();
    private int Acceleration_Mod = LIENAR_ACCELERATION, canvasWidth, canvasHeight, eventY, score = 0,numberOfGrade = 0, minutes, seconds;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillisconds;
    private String timer;
    private final static long CONVERT_TO_SECONDS = 1000, CONVERT_TO_MINUTES = 60000;
    private final static int LIENAR_ACCELERATION = 21, CONSTANT_ACCELERATION = 20, VELOCITY = 1, MAX_GRADE_IN_SCREEN = 7, CHANGE_TIMER_DISPLAY = 10;
    private boolean eventTouch;
    private final static double MIDDLE_SCREEN = 2.5;

    public GameView(Context context) {
        super(context);
        timeLeftInMillisconds = 30000;
        student = new Student(this);
        view = this;
        background = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        timerPaint.setColor(Color.WHITE);
        timerPaint.setTextSize(70);
        timerPaint.setTypeface(Typeface.DEFAULT_BOLD);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        if(eventTouch){
            if((canvasHeight/MIDDLE_SCREEN)-eventY>=0 && InMap(canvas,student)){
                    goUp();
            } if((canvasHeight/MIDDLE_SCREEN)-eventY <=0 && InMap(canvas,student)){
                    goDown();

            }
        }if(student.getY() == -1){
            student.setY((int) (canvasHeight/MIDDLE_SCREEN));
            startTimer();
        }

        CreateGrades(view);
        canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(student.getBitmap(),0,student.getY(),null);
        DrawListGrades(canvas);

        canvas.drawText("Score : "+score,20,60, scorePaint);
        canvas.drawText(""+timer,(int) (canvasWidth/MIDDLE_SCREEN),60, timerPaint);
        MoveListGrades();
        DetectAllCollission(student,grades);

    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillisconds, CONVERT_TO_SECONDS) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void updateTimer(){
        minutes = (int) (timeLeftInMillisconds / CONVERT_TO_MINUTES);
        seconds = (int) (timeLeftInMillisconds % CONVERT_TO_MINUTES/ CONVERT_TO_SECONDS);
        timer = minutes+":";
        if(seconds<CHANGE_TIMER_DISPLAY) {
            timer = timer+"0";
        }
        timer = timer + seconds;
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
                CreateGrade(view);
            }
        }

    }

    private  void CreateGrade(View view){
        Grade grade = new Grade(view);
        do {
            grade.setX(rand.nextInt(canvasHeight * 2 - canvasWidth) + canvasHeight + grade.getHeight());
            grade.setY(rand.nextInt((canvasHeight - grade.getHeight() - grade.getHeight())) + grade.getHeight());
        }while (DetectAllCollission(grade,grades));
        grades.add(grade);
    }

    private  void MoveListGrades(){
        for(int i =0; i<grades.size();i++){
            grades.get(i).move();
            if(grades.get(i).getX()<-grades.get(i).getWidth()){
                grades.remove(grades.get(i));
            }
        }
    }

    private void DrawListGrades(Canvas canvas){
        for(int i =0; i<grades.size();i++){
            canvas.drawBitmap(grades.get(i).getBitmap(), grades.get(i).getX(), grades.get(i).getY(),null);
        }
    }

    private boolean Collision(DrawableObject object1, DrawableObject object2){
        return object1.getX()<object2.getX()+object2.getWidth() && (object1.getX()+object2.getWidth())>object2.getX() && object1.getY()<object2.getY()+object2.getHeight() && object1.getY()+object2.getHeight()>object2.getY();
    }

    private boolean DetectAllCollission(DrawableObject object, ArrayList<Grade> grades){
        boolean collison = false;
        for(int i = 0;i<grades.size();i++){
            if(Collision(object,grades.get(i))){
                score = score + grades.get(i).getScore();
                numberOfGrade = numberOfGrade + 1;
                grades.remove(grades.get(i));
                collison = true;
            }
        }
        return  collison;
    }

}
