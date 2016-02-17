package com.example.alexander.sportapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.Map;

public class FindEventDetailActivity extends FragmentActivity implements OnMapReadyCallback  /*extends BaseMenu */ {

    private GoogleMap daMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_event_detail);

     //   ImplementBaseMenu();
     //   ImplementCommendHostButton();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        ImageView findEventDetailImageHeader = (ImageView) findViewById(R.id.findEventDetailImageHeader);

        BitmapFactory.Options bfo = new BitmapFactory.Options();
        bfo.inTargetDensity = 40;

        Bitmap findEventDetailImageHeaderBITMAP = BitmapFactory.decodeResource(getResources(), R.drawable.soccer, bfo);

        findEventDetailImageHeader.setImageBitmap(findEventDetailImageHeaderBITMAP);



        ImageView ProfilePicture = (ImageView) findViewById(R.id.FindEventDetailProfilePicture);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = 40;
        Bitmap ProfilePictureBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.alexzidros, options);
     //   Bitmap ProfilePictureBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.srre, options);
    //    RoundedBitmapDrawable ProfilePictureRoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), ProfilePictureBitmap);

        // make fully circular
   //     ProfilePictureRoundedBitmapDrawable.setCircular(true);

        // round corners
        // circlebit.setCornerRadius(300);

     //   new ImageDownloader().execute();



        ProfilePicture.setImageBitmap(ProfilePictureBitmap);




/*
        ImageView staticMap = (ImageView) findViewById(R.id.staticMap);

        String mapUrl = StaticGoogleMapURLMaker(43.659159,-79.4725638,18,600,250,"red");

        ImageURLDownloader(staticMap, mapUrl , 999);

        staticMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindEventDetailActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
*/


      //  Log.d("NIGGER", StaticGoogleMapURLMaker(43.659159, -79.4725638, 15, 500, 200, "red"));




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        daMap = googleMap;

        LatLng sydney = new LatLng(43.659159, -79.4725638);

        daMap.addMarker(new MarkerOptions().position(sydney).title("Ravina Gardens"));
        daMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
        daMap.animateCamera(CameraUpdateFactory.zoomTo(18), 3000, null);

    }





}




