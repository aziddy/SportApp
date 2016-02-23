package com.example.alexander.sportapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by alexander on 12/13/2015.
 */
public class BaseMenu extends AppCompatActivity {


 // git update

    int  DensityDpi;

    Context currentContext;

    Context HomeActContext;
    Context LoginActContext;
    Context FindHostContext;
    Context ClientProfileContext;






    BaseMenu () {



}



   public void ImplementBaseMenu() {


       final ImageView home = (ImageView) findViewById(R.id.homeMenu);
       Bitmap homebit = BitmapFactory.decodeResource(getResources(), R.drawable.home);
       homebit = Bitmap.createScaledBitmap(homebit, 100, 100, true);

       home.setImageBitmap(homebit);

       home.setOnClickListener( new View.OnClickListener() {

           @Override
           public void onClick(View view) {

           }

       });





       final ImageButton findhost_fab = (ImageButton) findViewById(R.id.findhost_fab);

      final BitmapFactory.Options findhost_fabFactory = new BitmapFactory.Options();

        findhost_fabFactory.inTargetDensity = 40;

         Bitmap findhost_fabBit = BitmapFactory.decodeResource(getResources(), R.drawable.findhost, findhost_fabFactory);



        findhost_fab.setImageBitmap(findhost_fabBit);




        findhost_fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Toast.makeText(getApplication(), "NIGGA", Toast.LENGTH_SHORT).show();


            //    findhost_fab.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

                Intent intentFindHost = new Intent(BaseMenu.this, FindHost.class);
                startActivity(intentFindHost);

            }

        });



       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
           }
       });








/*
       ImageView findhost = (ImageView) findViewById(R.id.findHostMenu);
       Bitmap findHostBit = BitmapFactory.decodeResource(getResources(), R.drawable.findhost);
   // Must Change resolution for different screen solutions \/ \/ \/
       findHostBit = Bitmap.createScaledBitmap(findHostBit, 100, 100, true);
       findhost.setImageBitmap(findHostBit);

       findhost.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {

               Intent intentfindhost = new Intent(BaseMenu.this, FindHost.class);
               startActivity(intentfindhost);

           }
       });
*/

       ImageView profile = (ImageView) findViewById(R.id.profileMenu);

       Bitmap profilebit = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
   // Must Change resolution for different screen solutions \/ \/ \/
       profilebit = Bitmap.createScaledBitmap(profilebit, 100, 100, true);
       profile.setImageBitmap(profilebit);

           profile.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View view) {

                   Intent intentprofile = new Intent(BaseMenu.this, clientProfile.class);

                  startActivity(intentprofile);

                //   overridePendingTransition(0,0);

               }
           });



ImageView settings = (ImageView) findViewById(R.id.settingsMenu);
       Bitmap settingsbit = BitmapFactory.decodeResource(getResources(), R.drawable.gear);
       settingsbit = Bitmap.createScaledBitmap(settingsbit, 100, 100, true);
       settings.setImageBitmap(settingsbit);

       settings.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
           Intent intent = new Intent(BaseMenu.this, ManageMyPickups.class);
               startActivity(intent);

           }

       });


       if(currentContext == HomeActContext) {



       } else if (currentContext == FindHostContext) {

           findhost_fab.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);

       } else if (currentContext == ClientProfileContext) {

           profile.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);

       }


   }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////IMPLEMENT COMMEND HOST BUTTON//////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void ImplementCommendHostButton() {

   LinearLayout button = (LinearLayout) findViewById(R.id.CommendHostButton);


    button.setOnClickListener(new View.OnClickListener(){

        @Override
    public void onClick(View view){

            Toast.makeText(BaseMenu.this, "wtf", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(BaseMenu.this, MapsActivity.class);
            startActivity(intent);

        }
    });

}





//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////START OF RESIZE PICTURE CODE///////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    /*////// USEAGE ///////

    mImageView.setImageBitmap(
    decodeSampledBitmapFromResource(getResources(), R.id.myimage, 100, 100));

    */

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////END OF RESIZE PICTURE CODE/////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void screenDensityDpi(int density){

        DensityDpi = density;


    }







    public String StaticGoogleMapURLMaker(double Lat, double Lng, int Zoom, int Xsize, int Ysize, String MarkerColor){

        String map = "http://maps.google.com/maps/api/staticmap";

        map += "?center=" + Lat + "," + Lng + "&zoom=" + Zoom + "&size=" + Xsize + "x" + Ysize + "&sensor=false" + "&markers=color:" + MarkerColor + "%7C" + "size:large%7C" + Lat + "," + Lng;

       // http://maps.google.com/maps/api/staticmap?center=43.659159,-79.4725638&zoom=15&size=500x200&sensor=false&markers=color:red%7C4343.659159,-79.4725638
       // http://maps.google.com/maps/api/staticmap?center=43.659159,-79.4725638&zoom=15&size=500x200&sensor=false&markers=color:red%7C43.659159,-79.4725638


         return map;
    }




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////IMAGE FROM URL CODE ASYNC//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






    ImageView ImageViewDownloaderURL = null;
    BitmapFactory.Options ImageViewDownloaderURLOptions = new BitmapFactory.Options();



    //     arg0         ImageView                 ImageView to display image on

    //     arg1         String                    Image URL on internet

    //     arg2         int                    BitmapFactory.Options Density (make image look shitty to use less memory)


    public void ImageURLDownloader(ImageView ImgView, String URL, int Density) {

        ImageViewDownloaderURLOptions.inTargetDensity = Density;
        ImageViewDownloaderURL = ImgView;
        new ImageDownloader().execute(URL);


    }





// Async stuff to process hard shit on another thread to prevent main UI thread from crashing

    class ImageDownloader extends AsyncTask<String, String, Bitmap> {



        protected Bitmap doInBackground(String... params){
            Bitmap bmp = null;


            try {


                URL url = new URL(params[0]);
                InputStream in = (InputStream) url.getContent();
                bmp = BitmapFactory.decodeStream(in,null,ImageViewDownloaderURLOptions);

            } catch (Exception e){


            }


            return bmp;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){

                ImageViewDownloaderURL.setImageBitmap(image);
                   Toast.makeText(BaseMenu.this, "NIGGA IT WORKED", Toast.LENGTH_SHORT).show();

            } else {

                    Toast.makeText(BaseMenu.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }

        }

    }

}









