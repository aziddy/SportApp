package com.example.alexander.sportapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Host_PickDateTime extends ActionBarActivity {

    String DateValue = "";
    String TimeValue = "";
    int EventDuration;
    String EventDurationTextValue = "";
    TextView timeTextView;
    TextView dateTextView;
    String discoveryProgess = "";

    int UserAge;

    Button setDateBtn;
    Button setTimeBtn;


            SharedPreferences settings;
    SharedPreferences.Editor HostEventDataPickEditor;

    SharedPreferences saveState;
    SharedPreferences.Editor saveStateEditor;

    //

    /** Declare listener too add to Touchevent Override */
    GestureDetectorCompat gDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_pick_date_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UserAge = 18;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        settings = getSharedPreferences("HostEventDataPick",  MODE_PRIVATE);
        HostEventDataPickEditor = settings.edit();


        ImageView nextButton = (ImageView) findViewById(R.id.rightButtonImage);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(EventDuration == 1){

                    Toast.makeText(getApplicationContext(), "Your event duration cant be 0. Fool", Toast.LENGTH_LONG).show();

                } else {
                    Intent intent = new Intent(Host_PickDateTime.this, Host_PickLocation.class);
                    startActivity(intent);
                }
            }
        });

        /** Set GestureListener */
        gDetect = new GestureDetectorCompat(this, new GestureListener());

        dateTextView = (TextView) findViewById(R.id.pickDateID);
        timeTextView = (TextView) findViewById(R.id.pickTimeID);
      //  Intent fromIntent = getIntent();


        saveState = getSharedPreferences("saveStateHost_PickDateTime", MODE_PRIVATE);
        saveStateEditor = saveState.edit();

       // Toast.makeText(getApplicationContext(), saveState.getString("DateValue", "") + "", Toast.LENGTH_LONG).show();

        DateValue = saveState.getString("DateValue", "");

        if (DateValue.length() < 3) {
            Date date = Calendar.getInstance().getTime();
            //
            // Display a date in day, month, year format
            //
            DateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
            String today = formatter.format(date);
            DateValue = today;

        }


        dateTextView.setText(DateValue);


        setDateBtn = (Button) findViewById(R.id.setDateBtn);

        setDateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /** takes custom made DatePickerFragment / Dialog and displays it in this activity **/
                SharedPreferences settings = getSharedPreferences("passActivityToDatePickerData", MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = settings.edit();

                // ignore the picktime
                prefEditor.putString("ActivityName", "Host_PickTime");
                prefEditor.commit();

                DialogFragment newFrag = new DatePickerFragment();
                newFrag.show(getFragmentManager(), "datePicker");

                /** ///////////////////////////////////////////////////////////////////////////// **/

            }
        });


        setTimeBtn = (Button) findViewById(R.id.setTimeBtn);


        setTimeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /** takes custom made DatePickerFragment / Dialog and displays it in this activity **/
                SharedPreferences settings = getSharedPreferences("passActivityToDatePickerData", MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = settings.edit();

                // ignore the picktime
                prefEditor.putString("ActivityName", "Host_PickTime");
                prefEditor.commit();

                DialogFragment newFrag = new TimePickerFragment();
                newFrag.show(getFragmentManager(), "timePicker");

                /** ///////////////////////////////////////////////////////////////////////////// **/

            }
        });

        SeekBar SeekBarHostEventLength = (SeekBar) findViewById(R.id.SeekBarHostEventLength);
        final TextView EventDurationValueText = (TextView) findViewById(R.id.EventDurationValueText);

        SeekBarHostEventLength.setMax(300);

        SeekBarHostEventLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress < 60) {

                    EventDurationTextValue = progress + " Minutes";
                    EventDurationValueText.setText(EventDurationTextValue);
                } else if (progress < 120) {

                    EventDurationTextValue = "1" + " Hour " + (progress - 60) + " Minutes";
                    EventDurationValueText.setText(EventDurationTextValue);

                } else if (progress < 180) {

                    EventDurationTextValue = "2" + " Hours " + (progress - 120) + " Minutes";
                    EventDurationValueText.setText(EventDurationTextValue);

                } else if (progress < 240) {

                    EventDurationTextValue = "3" + " Hours " + (progress - 180) + " Minutes";
                    EventDurationValueText.setText(EventDurationTextValue);

                } else if (progress < 300) {

                    EventDurationTextValue = "4" + " Hours " + (progress - 240) + " Minutes";
                    EventDurationValueText.setText(EventDurationTextValue);

                } else if (progress == 300) {

                    EventDurationTextValue = "5" + " Hours " + "0" + " Minutes";
                    EventDurationValueText.setText(EventDurationTextValue);
                }

                EventDuration = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                HostEventDataPickEditor.putInt("EventDuration", EventDuration);
                HostEventDataPickEditor.apply();
            }
        });


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

                HostEventDataPickEditor.putString("DiscoveryRange", discoveryProgess);
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
                HostEventDataPickEditor.putString("AgeRangeMax", Integer.toString(maxValue));
                HostEventDataPickEditor.apply();
            }
        });


    }


    public void dialogDateToActivity(String value){

        DateValue = value;
        dateTextView.setText(DateValue);
        setDateBtn.setText(DateValue);
        saveStateEditor.putString("DateValue", DateValue);
        saveStateEditor.commit();

        HostEventDataPickEditor.putString("Date", DateValue);
        HostEventDataPickEditor.apply();

        Toast.makeText(getApplicationContext(), settings.getString("Date", ""), Toast.LENGTH_SHORT).show();
    }


    public void dialogTimeToActivity (String value) {

        TimeValue = value;
        timeTextView.setText(TimeValue);
        setTimeBtn.setText(TimeValue);
        saveStateEditor.putString("TimeValue", TimeValue);
        saveStateEditor.commit();

        HostEventDataPickEditor.putString("Time", TimeValue);
        HostEventDataPickEditor.apply();

        Toast.makeText(getApplicationContext(), "TIME-WORKED", Toast.LENGTH_SHORT).show();

    }



    /** Must Override out of onCreate but in Actvitiy Class */
    @Override
    public boolean onTouchEvent(MotionEvent event){

        this.gDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


@Override
public void finish(){

    super.finish();


}


    /** GESTURE STUFF */

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


        /** FOWARD = LEFT               BACKWARD = RIGHT
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

            Toast.makeText(getApplicationContext(), "FOWARD", Toast.LENGTH_LONG).show();
        } else if (backward){
            Toast.makeText(getApplicationContext(), "BACKWARD" , Toast.LENGTH_LONG).show();
            finish();

        }

        return true;
    }




}




}
