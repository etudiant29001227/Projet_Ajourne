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
    private Intent intent;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new GameView(this);
        difficulty = getIntent().getIntExtra("difficulty",-1);
        game.setDifficulty(difficulty);
        setContentView(game);
        intent =  new Intent(getApplicationContext(),EditActivity.class);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                game.invalidate();
                checkEndGame();
            }
        },0,REFRESH);
    }

    public void checkEndGame(){
        if(game.isEndGame()){
            intent.putExtra("mark", game.getScore());
            timer.cancel();
            GameActivity.this.startActivity(intent);
            finish();
        }
    }
}
