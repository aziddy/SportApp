package com.example.alexander.sportapp;

import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by alexander on 1/18/2016.
 */
public class GestureListenerExampleCode {




    /** TODO place in activity class and out of onCreate Method */
    // GestureDetectorCompat gDetect;

    /** TODO place in onCreate Method */
   // gDetect = new GestureDetectorCompat(this, new GestureListener());




    /** Must Override out of onCreate but in Actvitiy Class */

    /** TODO put outside of onCreate Method but put in activity class */
/*
   @Override
    public boolean onTouchEvent(MotionEvent event){

        this.gDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

*/



/** TODO place class in activity class, out out onCreate method*/

/*
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

*/


        /** FOWARD = LEFT               BACKWARD = RIGHT
         *
         *  HORIZONTAL
         *
         */
    /*
        if(Math.abs(horizontalDiff) > Math.abs(verticalDiff) && Math.abs(horizontalDiff) > flingMin &&  (Math.abs(velocityX)) > VelocityMin){

            if (horizontalDiff > 0 ){

                backward = true;

            } else {
                foward = true;
            }

*/



            /** FOWARD = UP              BACKWARD = DOWN
             *
             *  VERTICAL
             *
             */

    /*
        } else if (Math.abs(verticalDiff) > flingMin && (Math.abs(velocityY))> VelocityMin){

            if (verticalDiff > 0 ){

                backward = true;

            }else {
                foward = true;
            }

        }

        */

             /** TODO FILL Foward and Backward with code */

 /*
        if(foward){
            Toast.makeText(getApplicationContext(), "FOWARD", Toast.LENGTH_LONG).show();
        } else if (backward){
            Toast.makeText(getApplicationContext(), "BACKWARD" , Toast.LENGTH_LONG).show();
        }


        return true;
    }


*/







}
