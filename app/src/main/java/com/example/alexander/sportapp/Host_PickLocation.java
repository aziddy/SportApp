package com.example.alexander.sportapp;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

public class Host_PickLocation extends ActionBarActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    SharedPreferences settings;

    SharedPreferences.Editor HostEventDataPickEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_pick_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        settings = getSharedPreferences("HostEventDataPick",  MODE_PRIVATE);
        HostEventDataPickEditor = settings.edit();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView nextButtonImg = (ImageView) findViewById(R.id.rightButtonImage);

        nextButtonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Host_PickLocation.this, Host_ReviewEvent.class);
                startActivity(intent);
            }
        });



        SupportMapFragment SMF = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        SMF.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        PlaceAutocompleteFragment paf = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_Host_Pick_Location);

        paf.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(getApplicationContext(), "" + place.getName(), Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), " "+ place.getLatLng().toString(), Toast.LENGTH_LONG).show();
                Log.d("WTF", place.getLatLng().toString());
                Log.d("WTF", getLatString(place.getLatLng().toString()));
                Log.d("WTF", getLngString(place.getLatLng().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 16));
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
                HostEventDataPickEditor.putString("LocationLat", getLatString(place.getLatLng().toString()));
                HostEventDataPickEditor.putString("LocationLng", getLngString(place.getLatLng().toString()));
                HostEventDataPickEditor.putString("LocationName", place.getName().toString());
                HostEventDataPickEditor.apply();

            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getApplicationContext(), "ERROR: " + status, Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googlemap){
        mMap = googlemap;




    }


    //         Returns Lat in string when fed place.getLatLng().toString() from Google Places API
    public String getLatString(String string){

        String Lat = "";
        Boolean next = false;


       for(int x = 0; x < string.length(); x++){



           if (string.charAt(x) == ',') {

               next = false;

           } else if (next){

               Lat = Lat + string.charAt(x);

           }

           if (string.charAt(x) == '(') {
               next = true;
           }
       }

         return Lat;
    }

    // Returns Lng in string when fed place.getLatLng().toString() from Google Places API
    public String getLngString(String string){

        String Lng = "";
        Boolean next = false;


        for(int x = 0; x < string.length(); x++){



            if (string.charAt(x) == ')') {

                next = false;

            } else if (next){

                 Lng = Lng + string.charAt(x);

            }

            if (string.charAt(x) == ',') {
                next = true;
            }
        }

        return Lng;
    }


}
