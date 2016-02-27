package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alexander on 12/29/2015.
 */
public class FindEventAdapter extends ArrayAdapter<FindEventData> {

    public FindEventAdapter(Context context, ArrayList<FindEventData> arrayList) {

        super(context , 0, arrayList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        FindEventData data = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.find_listview, parent, false);

        }



        ImageView sportImageHeaderFindPickup = (ImageView) convertView.findViewById(R.id.sportImageHeaderFindPickup);

       // AsyncImageSetter as = new AsyncImageSetter(getContext(), sportImageHeaderFindPickup, R.drawable.soccer );
      //  as.execute();

        String mapUrl = StaticGoogleMapURLMaker(43.659159,-79.4725638,18,600,250,"red");

        ImageURLDownloader(sportImageHeaderFindPickup, mapUrl , 999);


      //  Bitmap bm = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.soccer, bfo);

      //  sportImageHeaderFindPickup.setImageBitmap(bm);

        TextView sportTextFind = (TextView) convertView.findViewById(R.id.sportTextFind);
        TextView timeTextFind = (TextView) convertView.findViewById(R.id.timeTextFind);
        TextView locationTextFind = (TextView) convertView.findViewById(R.id.locationTextFind);
        TextView attendingTextFind = (TextView) convertView.findViewById(R.id.attendingTextFind);
        ImageView imageViewFind = (ImageView) convertView.findViewById(R.id.imageViewFind);


        sportTextFind.setText(data.sport);
        timeTextFind.setText(data.time);
        locationTextFind.setText(data.location);
        attendingTextFind.setText(data.attending);

      //   BitmapFactory.Options options = new BitmapFactory.Options();
      //    options.inTargetDensity = 20;
      //    Bitmap image = BitmapFactory.decodeResource(Resources.getSystem(),data.image, options);
      //   imageViewFind.setImageBitmap(image);

        return convertView;

    }


    public class AsyncImageSetter extends AsyncTask<Void, Void, Void> {

        private ImageView img;
        private int image_resId;
        private Bitmap bmp;
        private Context c;

       // ProgressDialog pb;

        public AsyncImageSetter(Context c, ImageView img, int image_ResId) {

            this.img = img;
            this.image_resId = image_ResId;
            this.c = c;

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         //   pb = ProgressDialog.show(getContext(), "WAIT", null, true, true);
        }

        @Override
        protected Void doInBackground(Void... params) {


            BitmapFactory.Options bfo = new BitmapFactory.Options();
            bfo.inTargetDensity = 30;
            bmp = BitmapFactory.decodeResource(c.getResources(), image_resId, bfo);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            img.setImageBitmap(bmp);
            bmp = null;
         //   pb.dismiss();
            super.onPostExecute(result);
        }

    }


    /** ---------------------------------------- GOOGLE MAP IMAGE GET --------------------------------   */


    public String StaticGoogleMapURLMaker(double Lat, double Lng, int Zoom, int Xsize, int Ysize, String MarkerColor){

        String map = "http://maps.google.com/maps/api/staticmap";

        map += "?center=" + Lat + "," + Lng + "&zoom=" + Zoom + "&size=" + Xsize + "x" + Ysize + "&sensor=false" + "&markers=color:" + MarkerColor + "%7C" + "size:large%7C" + Lat + "," + Lng;

        return map;
    }

    ImageView ImageViewDownloaderURL = null;
    BitmapFactory.Options ImageViewDownloaderURLOptions = new BitmapFactory.Options();

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
               // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

            } else {

              //  Toast.makeText(FindEventAdapter.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }

        }

    }



}
