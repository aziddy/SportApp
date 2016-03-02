package com.example.alexander.sportapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.Inet4Address;
import java.util.ArrayList;

/**
 * Created by alexa on 3/1/2016.
 */
public class MyLeagueHostListViewAdapter extends ArrayAdapter<HostMyLeagueListViewData> {

    MyLeagueHostListViewAdapter (Context context, ArrayList<HostMyLeagueListViewData> arrayList){

        super(context, 0, arrayList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HostMyLeagueListViewData data = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.myleagues_listview, parent, false);

        }



        /**    Color String Format     "255,163,24,79"      */

        TextView LeagueNameText = (TextView) convertView.findViewById(R.id.leagueNameText);
        TextView teamOneNameText = (TextView) convertView.findViewById(R.id.teamOneNameText);
        TextView teamTwoNameText = (TextView) convertView.findViewById(R.id.teamTwoNameText);
        View teamOneColor = (View) convertView.findViewById(R.id.teamOneColor);
        View teamTwoColor = (View) convertView.findViewById(R.id.teamTwoColor);
        View LeagueColor = (View) convertView.findViewById(R.id.LeagueColor);





        GradientDrawable one = (GradientDrawable) teamOneColor.getBackground();
        one.setColor(Color.argb(255, 232, 19, 161));

        GradientDrawable two = (GradientDrawable) teamTwoColor.getBackground();
     //   two.setColor();


        LeagueNameText.setText(data.LeagueName);
        teamOneNameText.setText(data.TeamName1);
        teamTwoNameText.setText(data.TeamName2);

        getR("255,420,23,356");




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
        Toast.makeText(getContext(), Integer.parseInt(result), Toast.LENGTH_LONG).show();
        return Integer.parseInt(result);
    }



}
