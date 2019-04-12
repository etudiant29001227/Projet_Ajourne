package com.example.projet_ajourn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private MyDBAdapter db;
    private EditText name;
    private Float mark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        db = new MyDBAdapter(this);
        db.open();
        Intent data = getIntent();
        name = (EditText) findViewById(R.id.edittext);
        mark =  data.getExtras().getFloat("mark",-1);

    }

    public void okClick(View v){
        Score newScore = new Score(0,name.getText().toString(),mark);
        Intent intent = new Intent(this,Table_Score_Activity.class);
        db.insertScore(newScore.getName(),newScore.getMark(),newScore.getResult());
        db.close();
        startActivity(intent);
        finish();
    }
}
