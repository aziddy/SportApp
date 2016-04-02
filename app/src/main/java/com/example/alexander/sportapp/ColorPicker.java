package com.example.alexander.sportapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorPicker extends Activity {

    GridView gv;
    Button cancelBtn;
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);


        sp = getSharedPreferences("ColorPickerToTeamCreate", MODE_PRIVATE);
        spe = sp.edit();


        spe.putString("color", "0");
        spe.apply();


        gv = (GridView) findViewById(R.id.grid);

        final ArrayList<String> data = new ArrayList<String>();
        data.add("255-255-0-255");
        data.add("255-255-0-0");
        data.add("255-0-255-0");
        data.add("255-0-0-255");
        data.add("255-255-255-255");
        data.add("255-55-0-55");
        data.add("255-102-0-102");
        data.add("255-204-102-0");
        data.add("255-255-255-0");
        data.add("255-0-255-255");
        data.add("255-102-102-51");
        data.add("255-255-0-102");
        data.add("255-0-51-0");
        data.add("255-153-255-102");
        data.add("255-153-124-243");
        data.add("255-0-124-243");
        data.add("255-129-44-0");
        data.add("255-90-51-0");
        data.add("255-98-178-255");
        data.add("255-66-111-8");
        data.add("255-103-135-8");
        data.add("255-254-90-0");
        data.add("255-254-142-254");
        data.add("255-12-0-107");


         ColorPickerAdapter adapter = new ColorPickerAdapter(getApplicationContext(), data);
       // ArrayAdapter<String> ap = new ArrayAdapter<String>(this-android.R.layout.simple_list_item_1- data);

        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spe.putString("color", data.get(position));
                spe.putString("colorPickerLast", "1");
                spe.apply();
              //  Toast.makeText(getApplicationContext()- Integer.toString(position)- Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
