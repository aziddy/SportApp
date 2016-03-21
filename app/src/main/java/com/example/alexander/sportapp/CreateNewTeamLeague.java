package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateNewTeamLeague extends AppCompatActivity {

    EditText et;
    ListView lv;
    PlayerListEditAdapter pleAdapter;
    ArrayList<PlayerListEditData> data;
    SharedPreferences userClientInfo;
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_team_league);

        sp = getSharedPreferences("UsernameFindToTeamCreate", MODE_PRIVATE);
        spe = sp.edit();

 /*       SharedPreferences.OnSharedPreferenceChangeListener spChange = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Toast.makeText(getApplicationContext(), "spChangeWork", Toast.LENGTH_SHORT).show();
            }
        }; */


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

        if (sp.getString("usernameFindLast", "null") == "1") {
            //  Toast.makeText(getApplicationContext(), "spChangeWork", Toast.LENGTH_SHORT).show();
            data.add(new PlayerListEditData(new String[]{sp.getString("usernamet", "null")}));
            pleAdapter = new PlayerListEditAdapter(getBaseContext(), data);
            lv.setAdapter(pleAdapter);
        }
        spe.putString("usernameFindLast", "0");
        spe.putString("usernamet", "nope");
        spe.apply();
    }

    public void uploadLeagueToServer(String leagueName, String rankSystem, String sport, String leagueType, Boolean privateVAR , String hostusername) {

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

                    //     Intent intent = new Intent(HostLeague_CreateOne.this, ManageMyPickups.class);
                    //    startActivity(intent);

                }

            }
        }
       // new AsyncClass().execute(leagueName, rankSystem, sport, leagueType, Boolean.toString(privateVAR), hostusername);

    }


}
