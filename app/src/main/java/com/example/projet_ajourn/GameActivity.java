package com.example.projet_ajourn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private GameView game;
    private final static int REFRESH = 5;
    private int difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);
        game = new GameView(this);
        difficulty = getIntent().getIntExtra("difficulty",-1);
        System.out.println("Hard Level : "+difficulty);
        game.setDifficulty(difficulty);
        setContentView(game);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                game.invalidate();
            }
        },0,REFRESH);
    }
}
