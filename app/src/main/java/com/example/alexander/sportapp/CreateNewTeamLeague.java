package com.example.alexander.sportapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CreateNewTeamLeague extends AppCompatActivity {

    ArrayList al;
    EditText et;
    ArrayAdapter aa;
    ListView lv;
    PlayerListEditAdapter pleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_team_league);

        et = (EditText) findViewById(R.id.editText);


     //   String[] data = {"a", "b"};

        ArrayList<PlayerListEditData> data = new ArrayList<PlayerListEditData>();

        data.add(new PlayerListEditData(new String[]{"a"}));
        data.add(new PlayerListEditData(new String[]{"b"}));
        data.add(new PlayerListEditData(new String[]{"b"}));
        data.add(new PlayerListEditData(new String[]{"b"}));
        data.add(new PlayerListEditData(new String[]{"b"}));
        data.add(new PlayerListEditData(new String[]{"b"}));
        data.add(new PlayerListEditData(new String[]{"b"}));

       // al = new ArrayList();

        lv = (ListView) findViewById(R.id.listView);

        pleAdapter = new PlayerListEditAdapter(getBaseContext(), data);
     //   aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, al);
     //   lv.setAdapter(aa);
        lv.setAdapter(pleAdapter);

        Button addTeamMate = (Button) findViewById(R.id.add);
        addTeamMate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      //          al.add(et.getText().toString());
       //         lv.setAdapter(aa);
            }
        });



    }
}
