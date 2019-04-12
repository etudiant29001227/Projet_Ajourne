package com.example.projet_ajourn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level_Activity extends AppCompatActivity {

    private Button d1,d2,d3,d4,d5;
    private Intent intent;
    private final static int DIFFICULTY_1 = 1, DIFFICULTY_2 = 2, DIFFICULTY_3 = 3, DIFFICULTY_4 = 4, DIFFICULTY_5 = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_);
        d1 = findViewById(R.id.difficulty1);
        d2 = findViewById(R.id.difficulty2);
        d3 = findViewById(R.id.difficulty3);
        d4 = findViewById(R.id.difficulty4);
        d5 = findViewById(R.id.difficulty5);
        intent = new Intent(getApplicationContext(),GameActivity.class);
        d1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { goToGme( DIFFICULTY_1); }
        });
        d2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {  goToGme( DIFFICULTY_2);}
        });
        d3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { goToGme( DIFFICULTY_3); }
        });
        d4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { goToGme( DIFFICULTY_4); }
        });
        d5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { goToGme( DIFFICULTY_5); }
        });
    }

    public void goToGme(int difficulty){
        intent.putExtra("difficulty", difficulty);
        Level_Activity.this.startActivity(intent);
        finish();
    }
}
