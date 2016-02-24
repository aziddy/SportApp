package com.example.alexander.sportapp;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class HostLeague_CreateOne extends AppCompatActivity {

     int activeCheckMark = 1;

    boolean selectedSportBoolean = false;
    boolean selectedLeagueTypeBoolean = false;

    String selectedLeagueType = "";
  //  boolean
  String[] data;

    SharedPreferences CreateLeague;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_league_create_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // SHARED PREF
        CreateLeague = getSharedPreferences("CreateLeague", MODE_PRIVATE);
        edit = CreateLeague.edit();
        // Removing Values from previous League Creates
        edit.putString("sport", "");
        edit.putString("rankSystem", "unranked");




        final EditText LeagueNameEditText = (EditText) findViewById(R.id.LeagueNameEditText);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        LinearLayout createLeagueBtn = (LinearLayout) findViewById(R.id.createLeagueBtn);

        createLeagueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getApplicationContext(), "CREATE EVENT", Toast.LENGTH_LONG).show();

                if (selectedLeagueTypeBoolean && selectedSportBoolean){

                    if (LeagueNameEditText.getText().toString().length() > 3){



                    } else {
                        Toast.makeText(getApplicationContext(), "League Name cant be under 4 letters", Toast.LENGTH_LONG).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Fill in all the fields", Toast.LENGTH_LONG).show();

                }

            }
        });


        //LeagueNameEditText.check



        data = new String[] {"Soccer", "American Football", "BasketBall", "Baseball", "Squash"};

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerLeagueCreateSport = (Spinner) findViewById(R.id.spinnerLeagueCreateSport);
        spinnerLeagueCreateSport.setAdapter(aa);

        selectedSportBoolean = true;

          spinnerLeagueCreateSport.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener(){

              @Override
              public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                  edit.putString("sport", getSport(position));
              }

              @Override
              public void onNothingSelected(AdapterView<?> arg0) {
                  // TODO Auto-generated method stub
              }

          });


        /** INTIALIZE IMAGE CIRCLES IN COMP TYPE SELECT */


        final ImageView RoundRobinImg = (ImageView) findViewById(R.id.RoundRobinImg);
        final ImageView EliminationImg = (ImageView) findViewById(R.id.EliminationImg);
        final ImageView divisionImg = (ImageView) findViewById(R.id.divisionImg);
        final ImageView manualImg = (ImageView) findViewById(R.id.manualImg);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




        /** -------------------------------------------------------------------------- */


      final Button unrankedLeagueCreateBtn = (Button) findViewById(R.id.unrankedLeagueCreateBtn);
        final Button rankedLeagueCreateBtn = (Button) findViewById(R.id.rankedLeagueCreateBtn);

        unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
        rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

        unrankedLeagueCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit.putString("rankSystem", "unranked");
                Toast.makeText(getApplicationContext(), "NIGGA LEFT", Toast.LENGTH_LONG).show();
                unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
                rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

            }
        });

        rankedLeagueCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit.putString("rankSystem", "ranked");
                Toast.makeText(getApplicationContext(), "NIGGA", Toast.LENGTH_LONG).show();
                rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
                unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

            }
        });



        /** /////////////////////////////////////////////////////////////////////////*/

        /**              Comp type select listeners             */



        // ---------------------------------- ROUND ROBIN ---------------------------------- //

        LinearLayout roundRobinBtn = (LinearLayout) findViewById(R.id.roundRobinBtn);
        roundRobinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Round Robin", Toast.LENGTH_SHORT).show();

                    RoundRobinImg.setImageResource(R.drawable.checkmark);
                    EliminationImg.setImageResource(0);
                    divisionImg.setImageResource(0);
                    manualImg.setImageResource(0);
                   selectedLeagueType = "RoundRobin";
                selectedLeagueTypeBoolean = true;

            }
        });

        // ---------------------------------- ELIM ---------------------------------- //

        LinearLayout EliminationBtn = (LinearLayout) findViewById(R.id.EliminationBtn);
        EliminationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Elim", Toast.LENGTH_SHORT).show();

                RoundRobinImg.setImageResource(0);
                EliminationImg.setImageResource(R.drawable.checkmark);
                divisionImg.setImageResource(0);
                manualImg.setImageResource(0);
                selectedLeagueType = "Elimination";
                selectedLeagueTypeBoolean = true;

            }
        });

        // ---------------------------------- DIVISION ---------------------------------- //

        LinearLayout divisionBtn = (LinearLayout) findViewById(R.id.divisionBtn);
        divisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoundRobinImg.setImageResource(0);
                EliminationImg.setImageResource(0);
                divisionImg.setImageResource(R.drawable.checkmark);
                manualImg.setImageResource(0);
                selectedLeagueType = "Division";
                selectedLeagueTypeBoolean = true;



            }
        });

        // ---------------------------------- MANUAL ---------------------------------- //

        LinearLayout manualBtn = (LinearLayout) findViewById(R.id.manualBtn);
        manualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoundRobinImg.setImageResource(0);
                EliminationImg.setImageResource(0);
                divisionImg.setImageResource(0);
                manualImg.setImageResource(R.drawable.checkmark);
                selectedLeagueType = "Manual";
                selectedLeagueTypeBoolean = true;


            }
        });






        RoundRobinImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });






        EliminationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


/** .setMargins(DpToPixels(1), DpToPixels(1), DpToPixels(1), DpToPixels(1));        FOR NOT CHECKMARK*/

        /** /setMargins(DpToPixels(0), DpToPixels(0), DpToPixels(0), DpToPixels(0));        FOR CHECKMARK*/

    }


    public static int DpToPixels(int dp){

        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    public void IfCanGoToNextPage(int whichPart) {



    }


     public String getSport(int position){
            return data[position];
     }


}
