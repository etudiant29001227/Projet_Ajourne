package com.example.projet_ajourn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {
    private EtudiantView etudiant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);
        etudiant = new EtudiantView(this);
        setContentView(etudiant);
    }
}
