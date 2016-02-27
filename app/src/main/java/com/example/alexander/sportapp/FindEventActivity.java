package com.example.alexander.sportapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FindEventActivity extends BaseMenu {


    SharedPreferences FindViewEventData;
    SharedPreferences.Editor FindViewEventDataEditor;

     ArrayList<FindEventData> arrayListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_event);

        ImplementBaseMenu();

        arrayListData = new ArrayList<FindEventData>();

        FindViewEventData = getSharedPreferences("FindEventDataListViewToDetail", MODE_PRIVATE);
        FindViewEventDataEditor = FindViewEventData.edit();


        ListView findListView = (ListView) findViewById(R.id.findListView);

        String[] items = new String[20];


        for(int x = 0; x < items.length;x++){

            items[x] = "whoa" + x;

        }




        FindEventData exampleData = new FindEventData("Soccer","Alexander Zidros", "4:55pm", "Ravina Park", "8 People Attending", R.drawable.srre, "43.659159", "-79.4725638");
        FindEventData exampleDataTwo = new FindEventData("Squash","Alexander Bramwell", "1:25pm", " High Park Fitness", "2 People Attending", R.drawable.srre, "43.659159", "-79.4725638");

        arrayListData.add(exampleDataTwo);

        for (int x = 0; x < 10; x++) {

            arrayListData.add(new FindEventData("Soccer","Alexander Zidros", "4:55pm", "Ravina Park", "8 People Attending", R.drawable.srre, "43.659159", "-79.4725638"));

        }




        findListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // arrayListData

                FindViewEventDataEditor.putString("a", "a");
                FindViewEventDataEditor.apply();

                Intent onClickDetailIntent = new Intent(FindEventActivity.this, FindEventDetailActivity.class);
                startActivity(onClickDetailIntent);

            }
        });

        arrayListData.add(exampleData);
        arrayListData.add(exampleDataTwo);

        FindEventAdapter adapter = new FindEventAdapter(this,arrayListData);

        findListView.setAdapter(adapter);

    //    arrayListData.add()










      //  ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

       // findListView.setAdapter(adapter);


    }

}
