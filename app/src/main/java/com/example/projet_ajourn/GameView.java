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
    private Paint score = new Paint();
    private int canvasWidth, canvasHeight, eventY;
    private boolean eventTouch;
    private final static double MIDDLE_SCREEN = 2.5;

    public GameView(Context context) {
        super(context);

        student = new Student(this);
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
            if(student.getY()-eventY>0){
                student.setY(student.getY() - student.getSpeed());
                //eventTouch = false;
            } else{
                student.setY(student.getY() + student.getSpeed());
                //eventTouch = false;
            }
        }if(student.getY() == 0){
            student.setY((int) (canvasHeight/MIDDLE_SCREEN));
        }
        canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(student.getBitmap(),0,student.getY(),null);
        canvas.drawText("Score : ",20,60,score);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            eventTouch = true;
            eventY = (int)event.getY();
        }if(event.getAction() == MotionEvent.ACTION_UP){
            eventTouch = false;
        }
        return true;
    }
}
