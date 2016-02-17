package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Host_ReviewEvent extends BaseMenu {

    int EventDuration = 0;
    String Date = "";
    String Time = "";
    String LocationLat = "";
    String LocationLng = "";
    String LocationName = "";
    String Sport = "";
    String DiscoveryRange = "";
    String AgeRangeMin = "";
    String AgeRangeMax = "";


    public final static String UploadEventDataURL = "http://zidros.ca/pickupappuploadevent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_review_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       SharedPreferences settings = getSharedPreferences("HostEventDataPick",  MODE_PRIVATE);


        /** Data taken from shared preferences from previous make event pages for preview of post **/

       Date = settings.getString("Date", "");
       Time = settings.getString("Time", "");
       LocationLat = settings.getString("LocationLat", "");
       LocationLng = settings.getString("LocationLng", "");
       LocationName = settings.getString("LocationName", "");
       Sport = settings.getString("Sport", "");
       DiscoveryRange = settings.getString("DiscoveryRange", "");
       AgeRangeMin = settings.getString("AgeRangeMin", "");
       AgeRangeMax = settings.getString("AgeRangeMax", "");


        TextView HostSportText = (TextView) findViewById(R.id.HostSportText);
        HostSportText.setText(Sport);

        TextView HostDateTimeText = (TextView) findViewById(R.id.HostDateTimeText);
        HostDateTimeText.setText(Time + " " + Date);
        HostDateTimeText.setTextSize((Time + " " + Date).length()/((0.09f)*(Time + " " + Date).length()));



        ImageView ProfilePicture = (ImageView) findViewById(R.id.FindEventDetailProfilePicture);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = 40;
        Bitmap ProfilePictureBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.srre, options);
        RoundedBitmapDrawable ProfilePictureRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), ProfilePictureBitmap);

        // make fully circular
        ProfilePictureRoundedBitmapDrawable.setCircular(true);


        ProfilePicture.setImageDrawable(ProfilePictureRoundedBitmapDrawable);



        ImageView staticMap = (ImageView) findViewById(R.id.staticMap);

        String mapUrl = StaticGoogleMapURLMaker(43.659159,-79.4725638,18,600,250,"red");

        ImageURLDownloader(staticMap, mapUrl, 999);

        staticMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Host_ReviewEvent.this, MapsActivity.class);
                startActivity(intent);
            }
        });



        Log.d("NIGGER", StaticGoogleMapURLMaker(43.659159, -79.4725638, 15, 500, 200, "red"));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button uploadPickUpEventDataBtn = (Button) findViewById(R.id.uploadPickUpEventDataBtn);
        uploadPickUpEventDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventDataMethod(Date, Time, LocationLat, LocationLng, LocationName, Sport, AgeRangeMin, AgeRangeMax);
            }
        });

    }


    public void EventDataMethod(String data, String time, String lat, String lng, String locationName, String sport, String MinAgeRange, String MaxAgeRange) {

        class EventDataClass extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute(){
                loading = ProgressDialog.show(Host_ReviewEvent.this, "WAIT NIGGA", null, true, true);
            }

            @Override
            protected String doInBackground(String... params){

                RegisterUserClass rc = new RegisterUserClass();

                HashMap<String,String> hm = new HashMap<String, String>();

                SharedPreferences sp = getSharedPreferences("StoredActiveUserDate", MODE_PRIVATE);


                hm.put("date", params[0]);
                hm.put("time", params[1]);
                hm.put("lat", params[2]);
                hm.put("lng", params[3]);
                hm.put("locationname", params[4]);
                hm.put("sport", params[5]);
                hm.put("minagerange", params[6]);
                hm.put("maxagerange", params[7]);
                hm.put("username", sp.getString("username", ""));
                hm.put("password", sp.getString("password", ""));

                return rc.sendPostRequest(UploadEventDataURL, hm);
                //   return "21";
            }

            @Override
            protected void onPostExecute(String s){
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                if ((s.charAt(0) == 'S') && (s.charAt(1) == 'u')){

                    SharedPreferences sp = getSharedPreferences("StoredActiveUserDate", MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sp.edit();
                   // spEditor.putString("username", usernameLogin.getText().toString());
                   // spEditor.putString("password", passwordLogin.getText().toString());

                    Intent intent = new Intent(Host_ReviewEvent.this, FindHost.class);
                    startActivity(intent);

                }

            }
        }
        new EventDataClass().execute(data, time, lat, lng, locationName, sport, MinAgeRange, MaxAgeRange);

    }

}
