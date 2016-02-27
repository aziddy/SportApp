package com.example.alexander.sportapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Host_PickSport extends AppCompatActivity {

    Boolean casualHostBtnSelected = true;
    Boolean competitiveHostBtnSelected = false;
    GestureDetectorCompat gDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_pick_sport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setNavigationIcon(R.drawable);


        /** Save data for export to DB later */
        SharedPreferences settings = getSharedPreferences("HostEventDataPick",  MODE_PRIVATE);
        final SharedPreferences.Editor edit = settings.edit();

        /**     @casual-competitive      @sport      */

       // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

     //   getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    //    getSupportActionBar().setDisplayShowHomeEnabled(true);




        final Button casualHostBtn = (Button) findViewById(R.id.casualHostBtn);
        final Button competitiveHostBtn = (Button) findViewById(R.id.competitiveHostBtn);

        ListView listview = (ListView) findViewById(R.id.hostTypeListView);

        String[] data = {"Soccer", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball", "Basketball"};

        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, data);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                Toast.makeText(getApplication(), value, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Host_PickSport.this, Host_PickDateTime.class);
                intent.putExtra("sport", value);

                edit.putString("Sport", value);
                edit.apply();

                startActivity(intent);
                //    v.setBackgroundColor(Color.argb(255,195,195,195));
            }


        });

        listview.setAdapter(aa);

        gDetect = new GestureDetectorCompat(this, new GestureListener());
        gDetect.setIsLongpressEnabled(false);



        casualHostBtn.setBackgroundColor(Color.argb(255, 195, 195, 195));

        casualHostBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                casualHostBtnSelected = true;
                competitiveHostBtnSelected = false;
                Toast.makeText(getApplicationContext(), "LEFT", Toast.LENGTH_LONG).show();
                casualHostBtn.setBackgroundColor(Color.argb(255, 195, 195, 195));
                competitiveHostBtn.setBackgroundColor(Color.argb(0, 195, 195, 195));
                competitiveHostBtn.setBackgroundResource(R.drawable.outline);
            }

        });




        competitiveHostBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                casualHostBtnSelected = false;
                competitiveHostBtnSelected = true;
                Toast.makeText(getApplicationContext(), "RIGHT", Toast.LENGTH_LONG).show();
                competitiveHostBtn.setBackgroundColor(Color.argb(255, 195, 195, 195));
                casualHostBtn.setBackgroundColor(Color.argb(0, 195, 195, 195));
                casualHostBtn.setBackgroundResource(R.drawable.outline);

            }

        });
/*
        if (casualHostBtnSelected == true) {
            casualHostBtn.setBackgroundColor(Color.argb(255, 195, 195, 195));
        }

        if(competitiveHostBtnSelected == true) {
            casualHostBtn.setBackgroundColor(Color.argb(0, 0, 0, 0));
            casualHostBtn.setBackgroundResource(R.drawable.outline);

        }
*/


    }


    @Override
    public boolean onTouchEvent(MotionEvent event){

        this.gDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public class GestureListener extends GestureDetector.SimpleOnGestureListener{


        @Override
        public boolean onDown(MotionEvent event){

            return true;

        }


        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){

            float horizontalDiff = event2.getX() - event1.getX();
            float verticalDiff = event2.getY() - event1.getY();

            float flingMin = Math.abs(100);
            float VelocityMin = Math.abs(100);

            Boolean foward = false;
            Boolean backward = false;


            /** FOWARD = 2 LEFT               BACKWARD = 2 RIGHT
             *
             *  HORIZONTAL
             *
             */
            if(Math.abs(horizontalDiff) > Math.abs(verticalDiff) && Math.abs(horizontalDiff) > flingMin &&  (Math.abs(velocityX)) > VelocityMin){

                if (horizontalDiff > 0 ){

                    backward = true;

                } else {
                    foward = true;
                }

            }

            if(foward){
                Intent intent = new Intent(Host_PickSport.this, Host_PickDateTime.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "FOWARD", Toast.LENGTH_LONG).show();
            } else if (backward){
                Toast.makeText(getApplicationContext(), "BACKWARD" , Toast.LENGTH_LONG).show();
               // finish();

            }

            return true;
        }




    }


}
