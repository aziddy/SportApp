package com.example.alexander.sportapp;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class HostLeague_CreateOne extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_league_create_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String[] data = {"Soccer", "American Football", "BasketBall", "Baseball", "Squash"};

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerLeagueCreateSport = (Spinner) findViewById(R.id.spinnerLeagueCreateSport);
        spinnerLeagueCreateSport.setAdapter(aa);



      final Button unrankedLeagueCreateBtn = (Button) findViewById(R.id.unrankedLeagueCreateBtn);
        final Button rankedLeagueCreateBtn = (Button) findViewById(R.id.rankedLeagueCreateBtn);

        unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
        rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

        unrankedLeagueCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "NIGGA LEFT", Toast.LENGTH_LONG).show();
               // unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 195, 195, 195));
                unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 	40, 150, 0));

               // rankedLeagueCreateBtn.setBackgroundColor(Color.argb(0, 195, 195, 195));
                rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

             //   rankedLeagueCreateBtn.setBackgroundResource(R.drawable.outline);

            }
        });

        rankedLeagueCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "NIGGA", Toast.LENGTH_LONG).show();
              //  rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 195, 195, 195));
          //      unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(0, 195, 195, 195));

                rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
                unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

             //   unrankedLeagueCreateBtn.setBackgroundResource(R.drawable.outline);


            }
        });

        LinearLayout roundRobinBtn = (LinearLayout) findViewById(R.id.roundRobinBtn);
        roundRobinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Round Robin", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout EliminationBtn = (LinearLayout) findViewById(R.id.EliminationBtn);

        EliminationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Elim", Toast.LENGTH_SHORT).show();
            }
        });



        /** shit for layout margin via JAVA code **/


        LinearLayout.LayoutParams parm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parm.setMargins(5, 5, 5, 5);


        // layout.addview()

        // or  this.setLayoutParams(lp);


    }

}
