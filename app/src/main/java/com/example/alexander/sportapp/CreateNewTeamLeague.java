package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateNewTeamLeague extends AppCompatActivity {

    EditText et;
    ListView lv;
    LinearLayout ll;
    PlayerListEditAdapter pleAdapter;
    ArrayList<PlayerListEditData> data;
    SharedPreferences userClientInfo;
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    SharedPreferences cpsp;
    SharedPreferences.Editor cpspe;

    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_team_league);

        sp = getSharedPreferences("UsernameFindToTeamCreate", MODE_PRIVATE);
        spe = sp.edit();

        cv = (CardView) findViewById(R.id.cardview);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),ColorPicker.class);
                startActivity(intent);
            }
        });

        cpsp = getSharedPreferences("ColorPickerToTeamCreate", MODE_PRIVATE);
        cpspe = cpsp.edit();

 /*       SharedPreferences.OnSharedPreferenceChangeListener spChange = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Toast.makeText(getApplicationContext(), "spChangeWork", Toast.LENGTH_SHORT).show();
            }
        }; */

         ll = (LinearLayout) findViewById(R.id.topBar);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(), "TopBar", Toast.LENGTH_SHORT).show();


            }
        });


        data = new ArrayList<PlayerListEditData>();

        userClientInfo = getSharedPreferences("StoredActiveUserDate", MODE_PRIVATE);

        data.add(new PlayerListEditData(new String[]{userClientInfo.getString("username", "noValue")}));

        lv = (ListView) findViewById(R.id.listView);

        pleAdapter = new PlayerListEditAdapter(getBaseContext(), data);

        lv.setAdapter(pleAdapter);



        Button testBtn = (Button) findViewById(R.id.testBtn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewTeamLeague.this, UsernameFind.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
     //   Toast.makeText(getApplicationContext(),  cpsp.getString("color", "0"), Toast.LENGTH_SHORT).show();
    //    Toast.makeText(getApplicationContext(),  cpsp.getString("colorPickerLast", "nope"), Toast.LENGTH_SHORT).show();
        if (sp.getString("usernameFindLast", "null").equals("1")) {
            //  Toast.makeText(getApplicationContext(), "spChangeWork", Toast.LENGTH_SHORT).show();
            data.add(new PlayerListEditData(new String[]{sp.getString("usernamet", "null")}));
            pleAdapter = new PlayerListEditAdapter(getBaseContext(), data);
            lv.setAdapter(pleAdapter);
        }

        if (cpsp.getString("colorPickerLast", "0").equals("1")) {

            // color code
            cv.setBackgroundColor(Color.argb(getAlpha(cpsp.getString("color", "0")),getR(cpsp.getString("color", "0")),getG(cpsp.getString("color", "0")),getB(cpsp.getString("color", "0"))));
            Toast.makeText(getApplicationContext(),  cpsp.getString("color", "0"), Toast.LENGTH_SHORT).show();
        }

        cpspe.putString("color", "0");
        cpspe.putString("colorPickerLast", "0");

        spe.putString("usernameFindLast", "0");
        spe.putString("usernamet", "0");

        cpspe.apply();
        spe.apply();
    }

    public void uploadLeagueToServer(String teamName, String teamColor, String currentLeague, String wins, String loses , String players, String rank, String admins) {

        class AsyncClass extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute(){
                //     loading = ProgressDialog.show(HostLeague_CreateOne.this, "WAIT", null, true, true);
            }

            @Override
            protected String doInBackground(String... params){

                RegisterUserClass rc = new RegisterUserClass();

                HashMap<String,String> hm = new HashMap<String, String>();

                hm.put("teamname", params[0]);
                hm.put("teamcolor", params[1]);
                hm.put("currentleague", params[2]);
                hm.put("wins", params[3]);
                hm.put("loses", params[4]);
                hm.put("players", params[5]);
                hm.put("rank", params[6]);
                hm.put("admins", params[7]);

                return rc.sendPostRequest("http://zidros.ca/sportappcreateteamleagueupload.php", hm);

            }

            @Override
            protected void onPostExecute(String s){
                //    pd.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                if ((s.charAt(0) == 'S') && (s.charAt(1) == 'u')){

                    //     Intent intent = new Intent(HostLeague_CreateOne.this, ManageMyPickups.class);
                    //    startActivity(intent);

                }

            }
        }

        new AsyncClass().execute(teamName, teamColor, currentLeague, wins,  loses , players, rank, admins);
       // new AsyncClass().execute(leagueName, rankSystem, sport, leagueType, Boolean.toString(privateVAR), hostusername);

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
