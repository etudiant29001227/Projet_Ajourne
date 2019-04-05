package com.example.projet_ajourn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.graphics.Canvas;

public class EtudiantView extends View {

    private Bitmap etudiant,background;
    private Paint score = new Paint();

    public EtudiantView(Context context) {
        super(context);

        etudiant = BitmapFactory.decodeResource(getResources(),R.drawable.charcater8bit);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        score.setColor(Color.WHITE);
        score.setTextSize(70);
        score.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(etudiant,0,0,null);
    }
}
