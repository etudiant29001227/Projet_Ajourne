package com.example.projet_ajourn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.Menu;

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Score s = adapter.getItem(menuInfo.position);

        if(item.getItemId()== R.id.action_delete){
                adapter.remove(s);
                dbAdapter.removeScore(s.getId());
                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void onDestroy(){
        dbAdapter.close();
        super.onDestroy();
    }
}
