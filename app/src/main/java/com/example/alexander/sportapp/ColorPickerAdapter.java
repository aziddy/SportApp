package com.example.alexander.sportapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by alexa on 3/21/2016.
 */
public class ColorPickerAdapter extends ArrayAdapter<String> {


    ColorPickerAdapter(Context context, ArrayList<String> al){


        super(context, 0, al);

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String data = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.color_picker_gridview_colorbox, parent, false);

        }

  /** fix */
       // CardView cv = convertView.findViewById(R.id.cv);






        return convertView;

    }



    public int getAlpha(String a){
        String result = "";

        for (int x = 0; x < a.length(); x++){
            if (a.charAt(x) == ','){
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
            if(a.charAt(x) == ',' && !(commaPass == 1)) {
                commaPass++;

            } else if (a.charAt(x) == ',' && (commaPass == 1)){
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
            if(a.charAt(x) == ',' && !(commaPass == 2)) {
                commaPass++;

            } else if (a.charAt(x) == ',' && (commaPass == 2)){
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
            if(a.charAt(x) == ',' && !(commaPass == 3)) {
                commaPass++;

            } else if (a.charAt(x) == ',' && (commaPass == 3)){
                x = a.length();

            } else if (commaPass == 3) {
                result = result + a.charAt(x);
            }
        }

        return Integer.parseInt(result);
    }



}
