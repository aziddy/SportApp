package com.example.alexander.sportapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ColorPickerAdapter extends ArrayAdapter<String> {


    ColorPickerAdapter(Context context, ArrayList<String> al){


        super(context, 0, al);

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String data = getItem(position);
        CardView cardView;
        View view;

        int dp = 60;
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);




        if(convertView == null){

      //      cardView = new CardView(getContext());

       //     cardView.setLayoutParams(new GridView.LayoutParams(pixels, pixels));
           // cardView.setBackgroundColor(Color.argb(255,255,0,255));
         //   cardView.setRadius(9);

            view = new View(getContext());
            view.setLayoutParams(new LinearLayout.LayoutParams(pixels, pixels));

            GradientDrawable shape = new GradientDrawable();
            shape.setCornerRadius(20);
            shape.setColor(Color.argb(getAlpha(data),getR(data),getG(data),getB(data)));
            view.setBackground(shape);
         //   cardView.setBackgroundColor(Color.argb(getAlpha(data),getR(data),getG(data),getB(data)));

        } else {

           // cardView = (CardView) convertView;
            view = (View) convertView;

        }



  /** fix */
    //    CardView cv = convertView.findViewById(R.id.cv);






        return view;
        //return cardView;
    }



    public int getAlpha(String a){
        String result = "";

        for (int x = 0; x < a.length(); x++){
            if (a.charAt(x) == '-'){
                x = a.length();
            } else {
                result = result + a.charAt(x);
            }
        }
        return Integer.parseInt(result);
    }


    public int getR (String a) {
        String result = "";
        int commaPass = 0;

        for (int x = 0; x < a.length(); x++){
            if(a.charAt(x) == '-' && !(commaPass == 1)) {
                commaPass++;

            } else if (a.charAt(x) == '-' && (commaPass == 1)){
                x = a.length();

            } else if (commaPass == 1) {
                result = result + a.charAt(x);
            }
        }
        //
        return Integer.parseInt(result);
    }


    public int getG (String a) {
        String result = "";
        int commaPass = 0;

        for (int x = 0; x < a.length(); x++){
            if(a.charAt(x) == '-' && !(commaPass == 2)) {
                commaPass++;

            } else if (a.charAt(x) == '-' && (commaPass == 2)){
                x = a.length();

            } else if (commaPass == 2) {
                result = result + a.charAt(x);
            }
        }

        return Integer.parseInt(result);
    }

    public int getB (String a) {
        String result = "";
        int commaPass = 0;

        for (int x = 0; x < a.length(); x++){
            if(a.charAt(x) == '-' && !(commaPass == 3)) {
                commaPass++;

            } else if (a.charAt(x) == '-' && (commaPass == 3)){
                x = a.length();

            } else if (commaPass == 3) {
                result = result + a.charAt(x);
            }
        }

        return Integer.parseInt(result);
    }



}
