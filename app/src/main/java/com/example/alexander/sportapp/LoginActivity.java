package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LoginActivity extends BaseMenu {

    EditText usernameLogin;
    EditText passwordLogin;

    public final static String LoginURL = "http://zidros.ca/pickupappuserlogin.php";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // GET CONTEXT FOR MENU BAR
        currentContext = getApplicationContext();

        LoginActContext = getApplicationContext();



         usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);

        Button SignUp = (Button) findViewById(R.id.SignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);


            }
        });



        // GET DENISTY NUMBER
        String msgdi = "DENSITY" ;

        DisplayMetrics dm = getResources().getDisplayMetrics();
        DensityDpi = dm.densityDpi;

        Log.d(msgdi, " " + getApplicationContext());


        /** LOGIN IN BUTTON **/
        Button loginbutton = (Button) findViewById(R.id.LoginButton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Intent login = new Intent(LoginActivity.this, FindHost.class);
             //   startActivity(login);
                preLoginUser();
            }

        });

        /** Skip login process **/
        Button skipToNextButton = (Button) findViewById(R.id.skipToNextButton);

        skipToNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(LoginActivity.this, FindHost.class);
                startActivity(login);
            }
        });

    }

    public void preLoginUser() {

        String username = usernameLogin.getText().toString();
        String password = passwordLogin.getText().toString();

        Boolean containSpaces = false;

        for (int x = 0; x < username.length(); x++){

            if (username.charAt(x) == ' '){
                containSpaces = true;
                x = username.length();
            }

        }

        for (int x = 0; x < password.length(); x++){

            if (password.charAt(x) == ' '){
                containSpaces = true;

            }

        }


        if (containSpaces) {

            Toast.makeText(getApplicationContext(),"No spaces",Toast.LENGTH_LONG).show();

        } else {

            userLogin(username, password);

        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




// registrating user


         public void userLogin(String username, String password) {

             class UserLoginClass extends AsyncTask<String, Void, String>{

                 ProgressDialog loading;


                 @Override
                 protected void onPreExecute(){
                     loading = ProgressDialog.show(LoginActivity.this, "WAIT", null, true, true);
                 }

                 @Override
                 protected String doInBackground(String... params){

                     RegisterUserClass rc = new RegisterUserClass();

                     HashMap<String,String> hm = new HashMap<String, String>();

                     hm.put("username", params[0]);
                     hm.put("password", params[1]);

                 return rc.sendPostRequest(LoginURL, hm);
                 //   return "21";
                 }

                 @Override
                 protected void onPostExecute(String s){
                 //    pd.dismiss();
                     Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                     if ((s.charAt(0) == 'S') && (s.charAt(1) == 'u')){

                         SharedPreferences sp = getSharedPreferences("StoredActiveUserDate", MODE_PRIVATE);
                         SharedPreferences.Editor spEditor = sp.edit();
                         spEditor.putString("username", usernameLogin.getText().toString());
                         spEditor.putString("password", passwordLogin.getText().toString());
                         spEditor.apply();

                         Intent intent = new Intent(LoginActivity.this, clientProfile.class);
                         startActivity(intent);

                     }

                 }
             }
             new UserLoginClass().execute(username, password);

         }







}
