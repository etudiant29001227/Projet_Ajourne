package com.example.projet_ajourn;
import android.content.Context;
import android.content.Intent;
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
    private ArrayList<Mark> marks = new ArrayList<>();
    private Random rand = new Random();
    private Paint scorePaint = new Paint(),timerPaint = new Paint();
    private int Acceleration_Mod = LIENAR_ACCELERATION, canvasWidth, canvasHeight, eventY, score = 0, numberOfMark = 0, minutes, seconds;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillisconds;
    private boolean eventTouch;
    private String timer;
    private final static long CONVERT_TO_SECONDS = 1000, CONVERT_TO_MINUTES = 60000;
    private final static int LIENAR_ACCELERATION = 21, CONSTANT_ACCELERATION = 20, VELOCITY = 1, MAX_MARK_IN_SCREEN = 7, CHANGE_TIMER_DISPLAY = 10, LICENCE_1 = 1,LICENCE_2 = 2, LICENCE_3 = 3, MASTER_1 = 4, MASTER_2 = 5;
    private final static double MIDDLE_SCREEN = 2.5;

    public GameView(Context context) {
        super(context);
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
        canvas.drawText(""+timer,(int) (canvasWidth*0.75),60, timerPaint);
        MoveListGrades();
        DetectAllCollission(student, marks);

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

        for(int i = 0; i< MAX_MARK_IN_SCREEN; i++){
            if(marks.size() < MAX_MARK_IN_SCREEN){
                CreateGrade(view);
            }
        }

    }

    private  void CreateGrade(View view){
        Mark mark = new Mark(view);
        do {
            mark.setX(rand.nextInt(canvasHeight * 2 - canvasWidth) + canvasHeight + mark.getHeight());
            mark.setY(rand.nextInt((canvasHeight - mark.getHeight() - mark.getHeight())) + mark.getHeight());
        }while (DetectAllCollission(mark, this.marks));
        this.marks.add(mark);
    }

    private  void MoveListGrades(){
        for(int i = 0; i< marks.size(); i++){
            marks.get(i).move();
            if(marks.get(i).getX()<-marks.get(i).getWidth()){
                marks.remove(marks.get(i));
            }
        }
    }

    private void DrawListGrades(Canvas canvas){
        for(int i = 0; i< marks.size(); i++){
            canvas.drawBitmap(marks.get(i).getBitmap(), marks.get(i).getX(), marks.get(i).getY(),null);
        }
    }

    private boolean Collision(DrawableObject object1, DrawableObject object2){
        return object1.getX()<object2.getX()+object2.getWidth() && (object1.getX()+object2.getWidth())>object2.getX() && object1.getY()<object2.getY()+object2.getHeight() && object1.getY()+object2.getHeight()>object2.getY();
    }

    private boolean DetectAllCollission(DrawableObject object, ArrayList<Mark> marks){
        boolean collison = false;
        for(int i = 0; i< marks.size(); i++){
            if(Collision(object, marks.get(i))){
                score = score + marks.get(i).getScore();
                numberOfMark = numberOfMark + 1;
                marks.remove(marks.get(i));
                collison = true;
            }
        }
        return  collison;
    }

    public void setDifficulty(int d){
        switch (d){
            case LICENCE_1:
                timeLeftInMillisconds = 60000;
                break;
                case LICENCE_2:
                    timeLeftInMillisconds = 50000;
                break;
                case LICENCE_3:
                    timeLeftInMillisconds = 40000;
                break;
                case MASTER_1:
                    timeLeftInMillisconds = 30000;
                break;
                case MASTER_2:
                    timeLeftInMillisconds = 20000;

        }
    }

}
