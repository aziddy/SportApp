package com.example.alexander.sportapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorRes;
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
        View leagueColor = (View) convertView.findViewById(R.id.leagueColor);

        TextView matchTime = (TextView) convertView.findViewById(R.id.matchTime);
        TextView matchDate = (TextView) convertView.findViewById(R.id.matchDate);


        GradientDrawable one = (GradientDrawable) teamOneColor.getBackground();
        one.setColor(Color.argb(getAlpha(data.TeamColor1), getR(data.TeamColor1), getG(data.TeamColor1), getB(data.TeamColor1)));

        GradientDrawable two = (GradientDrawable) teamTwoColor.getBackground();
        two.setColor(Color.argb(getAlpha(data.TeamColor2), getR(data.TeamColor2), getG(data.TeamColor2), getB(data.TeamColor2)));

        GradientDrawable LeagueColor = (GradientDrawable) leagueColor.getBackground();
        LeagueColor.setColor(Color.argb(getAlpha(data.LeagueColor), getR(data.LeagueColor), getG(data.LeagueColor), getB(data.LeagueColor)));

        LeagueNameText.setText(data.LeagueName);
        teamOneNameText.setText(data.TeamName1);
        teamTwoNameText.setText(data.TeamName2);

        matchTime.setText(data.MatchTime);
        matchDate.setText(data.MatchDate);


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
