package com.example.alexander.sportapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class ColorPicker extends Activity {

    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        gv = (GridView) findViewById(R.id.grid);

        ArrayList<String> data = new ArrayList<String>();
        data.add("255,255,0,255");
        data.add("255,255,0,0");
        data.add("255,0,255,0");
        data.add("255,0,0,255");
        data.add("255,255,255,255");
        data.add("255,55,0,55");


         ColorPickerAdapter adapter = new ColorPickerAdapter(getApplicationContext(), data);
       // ArrayAdapter<String> ap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data);

        gv.setAdapter(adapter);


    }
}
