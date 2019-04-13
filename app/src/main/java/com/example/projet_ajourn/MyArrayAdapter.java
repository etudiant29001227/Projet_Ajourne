package com.example.projet_ajourn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Score> {

    private final Context context;

    public MyArrayAdapter (Context context, ArrayList<Score> values){
        super(context,R.layout.cell_layout,values);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View cellView = convertView;

        if (cellView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cellView = inflater.inflate(R.layout.cell_layout, parent, false);
        }

        TextView namePlayerTextView = (TextView) cellView.findViewById(R.id.name_player);
        TextView markPlayerTextView = (TextView) cellView.findViewById(R.id.mark_player);
        TextView resultPlayerTextView = (TextView) cellView.findViewById(R.id.result_player);


        Score s = getItem(position);
        namePlayerTextView.setText(s.getName());
        markPlayerTextView.setText(s.getMark()+"/20");
        resultPlayerTextView.setText(s.getResult());

        return cellView;
    }
}
