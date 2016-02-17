package com.example.alexander.sportapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.florescu.android.rangeseekbar.RangeSeekBar;

public class Host_PickAge extends AppCompatActivity {

    int UserAge;
    String discoveryProgess = "";



String valuething = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_pick_age);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        UserAge = 18;


        ImageView rightImageButton = (ImageView) findViewById(R.id.rightButtonImage);

        rightImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Host_PickAge.this, Host_ReviewEvent.class);
                startActivity(intent);
            }
        });

        /** Save data for export to DB later */
       SharedPreferences settings = getSharedPreferences("HostEventDataPick", MODE_PRIVATE);
        final SharedPreferences.Editor HostEventDataPickEditor = settings.edit();




        final TextView discoverySeekBarValueText = (TextView) findViewById(R.id.discoverySeekBarValue);
        discoverySeekBarValueText.setText("1 Km");

        SeekBar seekBarDiscovery = (SeekBar) findViewById(R.id.seekBarDiscovery);

        seekBarDiscovery.setMax(39);

        seekBarDiscovery.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
             //   Toast.makeText(getApplicationContext(), "YOU JUST GOT ROASTED", Toast.LENGTH_SHORT).show();
                discoverySeekBarValueText.setText(Integer.toString(progress+1)+" Km");

                discoveryProgess = Integer.toString(progress+1);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                HostEventDataPickEditor.putString("DiscoveryRange",discoveryProgess);
                HostEventDataPickEditor.apply();

            }
        });


        RangeSeekBar<Integer> rangeSeekbar = new RangeSeekBar<Integer>(this);

        final TextView ageSeekAarValueText = (TextView) findViewById(R.id.AgeSeekBarValue);

        rangeSeekbar.setRangeValues(UserAge, 90);
        rangeSeekbar.setSelectedMinValue(20);
        rangeSeekbar.setSelectedMaxValue(88);
        rangeSeekbar.setTextAboveThumbsColor(Color.argb(255, 1, 1, 1));



        LinearLayout layout = (LinearLayout) findViewById(R.id.hostAgeLayout);
        layout.addView(rangeSeekbar);

        rangeSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                ageSeekAarValueText.setText(Integer.toString(minValue) + " - " + Integer.toString(maxValue));
                HostEventDataPickEditor.putString("AgeRangeMin", Integer.toString(minValue));
                HostEventDataPickEditor.putString("AgeRangeMax",Integer.toString(maxValue));
                HostEventDataPickEditor.apply();
            }
        });

      //  RangeSeekBar rangeSeekBarTextColorWithCode = (RangeSeekBar) findViewById(R.id.rangeSeekBarTextColorWithCode);
     //   rangeSeekBarTextColorWithCode.setTextAboveThumbsColorResource(android.R.color.holo_blue_bright);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
