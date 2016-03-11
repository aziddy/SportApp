package com.example.alexander.sportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class HostLeagueOrPickup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_league_or_pickup);


        FrameLayout PickupBtn = (FrameLayout) findViewById(R.id.MatchesBtn);

        PickupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostLeagueOrPickup.this, Host_PickSport.class);
                startActivity(intent);
            }
        });



        FrameLayout leagueBtn = (FrameLayout) findViewById(R.id.leagueBtn);

        leagueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostLeagueOrPickup.this, HostLeague_CreateOne.class);
                startActivity(intent);
            }
        });


    }
}
