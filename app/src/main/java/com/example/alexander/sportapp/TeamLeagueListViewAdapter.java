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

import java.util.ArrayList;

/**
 * Created by alexa on 3/29/2016.
 */
public class TeamLeagueListViewAdapter extends ArrayAdapter<TeamLeagueListViewData>{

   TeamLeagueListViewAdapter(Context context, ArrayList<TeamLeagueListViewData> arrayList){

   super(context,0,arrayList);

}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TeamLeagueListViewData data = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.teamleague_listview, parent, false);

        }


        /**    Color String Format     "255,163,24,79"      */
    //    TextView LeagueNameText = (TextView) convertView.findViewById(R.id.leagueNameText);

        String LeagueTempStorage = "";
        String temp = "";
        Boolean collect = false;

        for(int x = 0; x < data.Rank.length()-1; x++) {

            if (data.Rank.charAt(x) == ')'){

                collect = false;

                if (temp == "no") {

                    data.Rank = "what";
                    x = 999;


                }
                Toast.makeText(getContext(), Integer.toString(temp.length()), Toast.LENGTH_SHORT).show();
                temp = "";

            }

            if (collect){

                temp += data.Rank.charAt(x);

            }

              if (data.Rank.charAt(x) == '(') {

                  collect = true;

              }
          }


        TextView TeamName = (TextView) convertView.findViewById(R.id.teamName);
        TextView Wins = (TextView) convertView.findViewById(R.id.wins);
        TextView Losses = (TextView) convertView.findViewById(R.id.losses);
        TextView rank = (TextView) convertView.findViewById(R.id.standing);
        View TeamColor = (View) convertView.findViewById(R.id.teamColor);

        TeamName.setText(data.TeamName);
        Wins.setText(data.Wins);
        Losses.setText(data.Losses);
        rank.setText(data.Rank);
        TeamColor.setBackgroundColor(Color.argb(getAlpha(data.TeamColor), getR(data.TeamColor), getG(data.TeamColor), getB(data.TeamColor)));





        return convertView;

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
