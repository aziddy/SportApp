package com.example.alexander.sportapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

public class UsernameFind extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_find);

        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<String> data = new ArrayList<String>();

        data.add("alex");
        data.add("Ziddy");
        data.add("Ben");

        ArrayAdapter<String> aa = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, data);

        AutoCompleteTextView autocompletetextview = (AutoCompleteTextView) findViewById(R.id.autocompletetextview);

        autocompletetextview.setAdapter(aa);


    }
}
