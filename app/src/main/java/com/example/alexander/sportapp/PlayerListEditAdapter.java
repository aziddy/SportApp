package com.example.alexander.sportapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alexa on 3/15/2016.
 */
public class PlayerListEditAdapter extends ArrayAdapter<PlayerListEditData> {

    ArrayList<PlayerListEditData> arrayList;
    Context context;

    PlayerListEditAdapter(Context context, ArrayList<PlayerListEditData> arrayList){

        super(context, 0, arrayList);
        this.arrayList = arrayList;
        this.context = context;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        PlayerListEditData pled = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.playerlist_edit_listview, parent, false);

        }

        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        ImageView removeBtn = (ImageView) convertView.findViewById(R.id.removeBtn);

        textView.setText(pled.userName);

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 arrayList.remove(position);
            }
        });

        return convertView;

    }
}


