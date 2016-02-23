package com.example.alexander.sportapp;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class HostLeague_CreateOne extends AppCompatActivity {

     int activeCheckMark = 1;

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


        /** INTIALIZE IMAGE CIRCLES IN COMP TYPE SELECT */


        final ImageView RoundRobinImg = (ImageView) findViewById(R.id.RoundRobinImg);
        final FrameLayout.LayoutParams RoundRobinImgParm = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        RoundRobinImgParm.setMargins(DpToPixels(1), DpToPixels(1), DpToPixels(1), DpToPixels(1));
        RoundRobinImg.setLayoutParams(RoundRobinImgParm);


        final ImageView EliminationImg = (ImageView) findViewById(R.id.EliminationImg);
        final FrameLayout.LayoutParams EliminationImgParm = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        EliminationImgParm.setMargins(DpToPixels(0), DpToPixels(0), DpToPixels(0), DpToPixels(0));
        EliminationImg.setLayoutParams(EliminationImgParm);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        final FrameLayout.LayoutParams checkmarkParm = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        checkmarkParm.setMargins(DpToPixels(0), DpToPixels(0), DpToPixels(0), DpToPixels(0));


        final FrameLayout.LayoutParams notcheckmarkParm = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        notcheckmarkParm.setMargins(DpToPixels(1), DpToPixels(1), DpToPixels(1), DpToPixels(1));


        /** -------------------------------------------------------------------------- */


      final Button unrankedLeagueCreateBtn = (Button) findViewById(R.id.unrankedLeagueCreateBtn);
        final Button rankedLeagueCreateBtn = (Button) findViewById(R.id.rankedLeagueCreateBtn);

        unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
        rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

        unrankedLeagueCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "NIGGA LEFT", Toast.LENGTH_LONG).show();
                // unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 195, 195, 195));
                unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));

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



            }
        });



        /** /////////////////////////////////////////////////////////////////////////*/

        /**              Comp type select listeners             */



        LinearLayout roundRobinBtn = (LinearLayout) findViewById(R.id.roundRobinBtn);
        roundRobinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Round Robin", Toast.LENGTH_SHORT).show();

                if (getActiveCheckMark() == 0) {


                } else {
                    RoundRobinImg.setLayoutParams(checkmarkParm);
                    RoundRobinImg.setImageResource(R.drawable.checkmark);

                    EliminationImg.setLayoutParams(notcheckmarkParm);
                    EliminationImg.setImageResource(R.drawable.notcheckmark);

                }

            }
        });

        LinearLayout EliminationBtn = (LinearLayout) findViewById(R.id.EliminationBtn);

        EliminationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Elim", Toast.LENGTH_SHORT).show();

                EliminationImg.setLayoutParams(checkmarkParm);
                EliminationImg.setImageResource(R.drawable.checkmark);
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

    public int getActiveCheckMark() {

       return activeCheckMark;

    }


    public void setActiveCheckMark(int pos) {

        activeCheckMark = pos;

    }


}
