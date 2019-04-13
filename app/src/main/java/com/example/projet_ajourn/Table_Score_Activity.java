package com.example.projet_ajourn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Table_Score_Activity extends AppCompatActivity {

    private ListView mListView;
    private MyArrayAdapter adapter;
    private MyDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table__score);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();

        mListView = findViewById(R.id.list);
        registerForContextMenu(mListView);

        adapter = new MyArrayAdapter(this, dbAdapter.getAllScores());
        mListView.setAdapter(adapter);

    }
}
