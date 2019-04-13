package com.example.projet_ajourn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity{

    private Button start,score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.start = findViewById(R.id.start);
        this.score = findViewById(R.id.score);
        start.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Level_Activity.class);
                startActivity(otherActivity);
                finish();

            }
        });
        score.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),Table_Score_Activity.class);
                startActivity(otherActivity);
                finish();

            }
        });


    }
}
