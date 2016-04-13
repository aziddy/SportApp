package com.example.alexander.sportapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TeamLeagueDetail extends AppCompatActivity {


    SharedPreferences sf;
    SharedPreferences.Editor sfe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_league_detail);

        sf = getSharedPreferences("DataToLeagueTeamDetail", MODE_PRIVATE);



        FrameLayout TeamColor = (FrameLayout) findViewById(R.id.TeamColor);
        TextView TeamName = (TextView) findViewById(R.id.TeamNameTextView);

        TeamName.setText(sf.getString("TeamName", "noValue"));


        ListView listview = (ListView) findViewById(R.id.listview);

        String[] data = {"1","2","3","4"};

        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data);


    }
}
