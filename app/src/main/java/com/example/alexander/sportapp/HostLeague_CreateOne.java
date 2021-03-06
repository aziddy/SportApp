package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class HostLeague_CreateOne extends AppCompatActivity {

     int activeCheckMark = 1;

    boolean selectedSportBoolean = false;
    boolean selectedLeagueTypeBoolean = false;

    String selectedLeagueType = "";
  //  boolean
    String[] data;

    SharedPreferences CreateLeague;
    SharedPreferences.Editor edit;

    SharedPreferences userClientInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_league_create_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // SHARED PREF
        CreateLeague = getSharedPreferences("CreateLeague", MODE_PRIVATE);
        edit = CreateLeague.edit();

        userClientInfo = getSharedPreferences("StoredActiveUserDate", MODE_PRIVATE);

        // Removing Values from previous League Creates
        edit.putString("leagueName", "");
        edit.putString("sport", "");
        edit.putString("rankSystem", "unranked");
        edit.putString("leagueType", "");
        edit.putBoolean("skipToLeague", false);

        /** need to set button for **/
        edit.putBoolean("privateVAR", true);




        final EditText LeagueNameEditText = (EditText) findViewById(R.id.LeagueNameEditText);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final LinearLayout createLeagueBtn = (LinearLayout) findViewById(R.id.createLeagueBtn);

        createLeagueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(), "CREATE EVENT", Toast.LENGTH_LONG).show();

                if (selectedLeagueTypeBoolean && selectedSportBoolean) {

                    if (LeagueNameEditText.getText().toString().length() > 3) {

                        edit.putString("leagueType", selectedLeagueType);
                        edit.putBoolean("skipToLeague", true);
                        edit.putString("leagueName", LeagueNameEditText.getText().toString());
                        edit.apply();


                       uploadLeagueToServer(CreateLeague.getString("leagueName", "noValue"), CreateLeague.getString("rankSystem", "noValue"), CreateLeague.getString("sport", "noValue"), CreateLeague.getString("leagueType", "noValue"), CreateLeague.getBoolean("privateVAR", true), userClientInfo.getString("username", "noValue"));


                  //      Intent intent = new Intent(HostLeague_CreateOne.this, ManageMyPickups.class);
                  //      finish();
                   //     startActivity(intent);



                    } else {
                        Toast.makeText(getApplicationContext(), "League Name cant be under 4 letters", Toast.LENGTH_LONG).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Fill in all the fields", Toast.LENGTH_LONG).show();

                }

            }
        });




        data = new String[] {"Soccer", "American Football", "BasketBall", "Baseball", "Squash"};

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerLeagueCreateSport = (Spinner) findViewById(R.id.spinnerLeagueCreateSport);
        spinnerLeagueCreateSport.setAdapter(aa);

        selectedSportBoolean = true;

          spinnerLeagueCreateSport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

              @Override
              public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                  edit.putString("sport", getSport(position));
                  Toast.makeText(getApplication(), getSport(position), Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onNothingSelected(AdapterView<?> arg0) {}


          });


        /** INTIALIZE IMAGE CIRCLES IN COMP TYPE SELECT */


        final ImageView RoundRobinImg = (ImageView) findViewById(R.id.RoundRobinImg);
        final ImageView EliminationImg = (ImageView) findViewById(R.id.EliminationImg);
        final ImageView divisionImg = (ImageView) findViewById(R.id.divisionImg);
        final ImageView manualImg = (ImageView) findViewById(R.id.manualImg);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




        /** -------------------------------------------------------------------------- */


      final Button unrankedLeagueCreateBtn = (Button) findViewById(R.id.unrankedLeagueCreateBtn);
        final Button rankedLeagueCreateBtn = (Button) findViewById(R.id.rankedLeagueCreateBtn);

        unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
        rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

        unrankedLeagueCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit.putString("rankSystem", "unranked");
                Toast.makeText(getApplicationContext(), "LEFT", Toast.LENGTH_LONG).show();
                unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
                rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

            }
        });

        rankedLeagueCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit.putString("rankSystem", "ranked");
                Toast.makeText(getApplicationContext(), "RIGHT", Toast.LENGTH_LONG).show();
                rankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 40, 150, 0));
                unrankedLeagueCreateBtn.setBackgroundColor(Color.argb(255, 61, 193, 0));

            }
        });



        /** /////////////////////////////////////////////////////////////////////////*/

        /**              Comp type select listeners             */



        // ---------------------------------- ROUND ROBIN ---------------------------------- //

        LinearLayout roundRobinBtn = (LinearLayout) findViewById(R.id.roundRobinBtn);
        roundRobinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Round Robin", Toast.LENGTH_SHORT).show();

                RoundRobinImg.setImageResource(R.drawable.checkmark);
                EliminationImg.setImageResource(0);
                divisionImg.setImageResource(0);
                manualImg.setImageResource(0);
                selectedLeagueType = "RoundRobin";
                selectedLeagueTypeBoolean = true;

            }
        });

        // ---------------------------------- ELIM ---------------------------------- //

        LinearLayout EliminationBtn = (LinearLayout) findViewById(R.id.EliminationBtn);
        EliminationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Elim", Toast.LENGTH_SHORT).show();

                RoundRobinImg.setImageResource(0);
                EliminationImg.setImageResource(R.drawable.checkmark);
                divisionImg.setImageResource(0);
                manualImg.setImageResource(0);
                selectedLeagueType = "Elimination";
                selectedLeagueTypeBoolean = true;

            }
        });

        // ---------------------------------- DIVISION ---------------------------------- //

        LinearLayout divisionBtn = (LinearLayout) findViewById(R.id.divisionBtn);
        divisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoundRobinImg.setImageResource(0);
                EliminationImg.setImageResource(0);
                divisionImg.setImageResource(R.drawable.checkmark);
                manualImg.setImageResource(0);
                selectedLeagueType = "Division";
                selectedLeagueTypeBoolean = true;



            }
        });

        // ---------------------------------- MANUAL ---------------------------------- //

        LinearLayout manualBtn = (LinearLayout) findViewById(R.id.manualBtn);
        manualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoundRobinImg.setImageResource(0);
                EliminationImg.setImageResource(0);
                divisionImg.setImageResource(0);
                manualImg.setImageResource(R.drawable.checkmark);
                selectedLeagueType = "Manual";
                selectedLeagueTypeBoolean = true;


            }
        });






        RoundRobinImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });






        EliminationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


/** .setMargins(DpToPixels(1), DpToPixels(1), DpToPixels(1), DpToPixels(1));        FOR NOT CHECKMARK*/

        /** /setMargins(DpToPixels(0), DpToPixels(0), DpToPixels(0), DpToPixels(0));        FOR CHECKMARK*/

    }


    public static int DpToPixels(int dp){

        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }




     public String getSport(int position){
            return data[position];
     }



    public void uploadLeagueToServer(String leagueName, String rankSystem, String sport, String leagueType, Boolean privateVAR , String hostusername) {

        class UserLoginClass extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute(){
           //     loading = ProgressDialog.show(HostLeague_CreateOne.this, "WAIT", null, true, true);
            }

            @Override
            protected String doInBackground(String... params){

                RegisterUserClass rc = new RegisterUserClass();

                HashMap<String,String> hm = new HashMap<String, String>();

                hm.put("leaguename", params[0]);
                hm.put("ranksystem", params[1]);
                hm.put("sport", params[2]);
                hm.put("leaguetype", params[3]);
                hm.put("private", params[4]);
                hm.put("hostusername", params[5]);


                return rc.sendPostRequest("http://zidros.ca/sportappcreateleagueupload.php", hm);

            }

            @Override
            protected void onPostExecute(String s){
                //    pd.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                if ((s.charAt(0) == 'S') && (s.charAt(1) == 'u')){

                    edit.putBoolean("skipToLeague", true);
                    edit.apply();
               //     Intent intent = new Intent(HostLeague_CreateOne.this, ManageMyPickups.class);
                //    startActivity(intent);

                }

            }
        }
        new UserLoginClass().execute(leagueName, rankSystem, sport, leagueType, Boolean.toString(privateVAR) , hostusername);

    }




}
