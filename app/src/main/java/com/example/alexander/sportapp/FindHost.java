package com.example.alexander.sportapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class FindHost extends BaseMenu {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_host);

        // implement onClicklistener for menu



        currentContext = getApplicationContext();

        FindHostContext = getApplicationContext();

          ImplementBaseMenu();

         FrameLayout hostFrame = (FrameLayout) findViewById(R.id.hostFrame);

        hostFrame.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Toast.makeText(getApplication(), "WHAT NO NIGGA HOST", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(FindHost.this, Host_PickSport.class);
                 startActivity(intent);


          }
         }
        );

        FrameLayout findFrame = (FrameLayout) findViewById(R.id.findFrame);

        findFrame.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {

              Toast.makeText(getApplication(), "FRAME", Toast.LENGTH_SHORT).show();
            //  setContentView(R.layout.find_listview);

              Intent intent = new Intent(FindHost.this,FindEventActivity.class);
              startActivity(intent);

          }
         }
        );

        Button leagueBtn = (Button) findViewById(R.id.leagueBtn);

        leagueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindHost.this, HostLeague_CreateOne.class);
                startActivity(intent);
            }
        });

    }


}
