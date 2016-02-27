package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    public final static String RegisterURL = "http://zidros.ca/pickupappuserreg.php";


    EditText usernameSignup = null;
    EditText passwordSignup = null ;
    EditText emailSignup = null;
    EditText fullnameSignUp = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

         usernameSignup = (EditText) findViewById(R.id.usernameSignUp);
         fullnameSignUp = (EditText) findViewById(R.id.fullnameSignUp);
         passwordSignup = (EditText) findViewById(R.id.passwordSignUp);
         emailSignup = (EditText) findViewById(R.id.emailSignUp);

        Button signUp = (Button) findViewById(R.id.SignUpBtn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    // REGISTER USER SHIT

    public void registerUser () {

        String username = usernameSignup.getText().toString();
        String fullname = fullnameSignUp.getText().toString();
        String password = passwordSignup.getText().toString();
        String email = emailSignup.getText().toString();

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

        for (int x = 0; x < email.length(); x++){

            if (email.charAt(x) == ' '){
                containSpaces = true;
                x = email.length();
            }

        }

        if (containSpaces) {

            Toast.makeText(getApplicationContext(),"No spaces",Toast.LENGTH_LONG).show();

        } else {

            register(username, password, email, fullname);

        }

    }



    public void register(String Username, String Password, String Email, String Fullname){
        // class with network stuff


        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            RegisterUserClass RUC = new RegisterUserClass();
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUpActivity.this, "WAIT", null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {

                String result =  "nothing";

                HashMap<String ,String> data = new HashMap<String, String>();

                data.put("username", params[0]);
                data.put("password", params[1]);
                data.put("email", params[2]);
                // data.put("fullname",params[4]);

                result = RUC.sendPostRequest(RegisterURL,data);

                return result;

            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                if (result.charAt(0) == 's'){
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);

                }


            }
        }

        new SendPostReqAsyncTask().execute(Username, Password, Email, Fullname);

    }

}
