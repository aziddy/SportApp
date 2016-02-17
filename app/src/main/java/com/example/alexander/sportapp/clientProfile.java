package com.example.alexander.sportapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.OutputStream;

public class clientProfile extends BaseMenu {







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);



        currentContext = getApplicationContext();

        ClientProfileContext = getApplicationContext();

        ImplementBaseMenu();







        // Gets screen density


        ImageView clientHeader = (ImageView) findViewById(R.id.clientHeader);
  // clientHeader.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
    //    clientHeader.setImageAlpha(50);
BitmapFactory.Options clientHeaderFactory = new BitmapFactory.Options();

        clientHeaderFactory.inTargetDensity = 50;

        // changing density settings as of decoding, lowers the image density prior to loading in memory
        Bitmap clientHeaderBit = BitmapFactory.decodeResource(getResources(), R.drawable.torontbg, clientHeaderFactory);

        // using the way below also lowers the image quality but does it after loading the picture into memory SO DONT USE IT
      //  clientHeaderBit = Bitmap.createScaledBitmap(clientHeaderBit, 100, 100, true);

        // set bitmap to imageView
           clientHeader.setImageBitmap(clientHeaderBit);



      //  ImageView clientProfilePic = (ImageView) findViewById(R.id.clientProfilePic);

        BitmapFactory.Options clientProfilePicFactory = new BitmapFactory.Options();

        clientProfilePicFactory.inTargetDensity = 30;

        final Bitmap clientProfilePicBit = BitmapFactory.decodeResource(getResources(), R.drawable.srre, clientProfilePicFactory);

     //   clientProfilePic.setImageBitmap(clientProfilePicBit);


      /*  final ImageButton findhost_fab = (ImageButton) findViewById(R.id.findhost_fab);

           BitmapFactory.Options findhost_fabFactory = new BitmapFactory.Options();

        findhost_fabFactory.inTargetDensity = 30;

        Bitmap findhost_fabBit = BitmapFactory.decodeResource(getResources(), R.drawable.findhost, findhost_fabFactory);


        findhost_fab.setImageBitmap(findhost_fabBit);

        findhost_fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {




            }

        }); */


















        //  outStream = new


      //  headerPicBit.compress(Bitmap.CompressFormat.PNG, 10, o);


    }


    @Override
    public void onPause() {
        // part of activity class
        super.onPause();  // Always call the superclass method first



// findhost.setImageDrawable(null);


        }



}
