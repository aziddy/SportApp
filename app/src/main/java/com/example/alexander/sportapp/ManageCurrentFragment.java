package com.example.alexander.sportapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alexander.sportapp.FindEventAdapter;
import com.example.alexander.sportapp.FindEventData;
import com.example.alexander.sportapp.HostMyLeagueListViewData;
import com.example.alexander.sportapp.R;

import java.util.ArrayList;


/**
 * Created by alexa on 3/1/2016.
 */
public class ManageCurrentFragment extends Fragment implements View.OnClickListener {

    public ManageCurrentFragment() {
        // Required empty public constructor

    }

    LayoutInflater inflater;
    ViewGroup container;

    String activityName = "";
    Boolean pickup = false;
    Boolean league = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);


        /**------------------------ get which activity the fragment is in ----------------------------**/

        for (int x = (getActivity().toString().length())-1; x > -1; x--){

            if (getActivity().toString().charAt(x) == '.') {

                for (int y = x+1; y < (getActivity().toString().length())-1; y++){
                    if (getActivity().toString().charAt(y) == '@') {

                        y = getActivity().toString().length()+40;

                    } else {
                        activityName += getActivity().toString().charAt(y);

                    }
                }
                Toast.makeText(getActivity(), activityName, Toast.LENGTH_LONG).show();

                if (activityName.equals("ManageMyPickups")) {
                    pickup = true;
                }

                if (activityName.equals("ManageMyLeagues")) {
                    league = true;
                }

                x = -1;
            }
        }
        /** ----------------------------------------------------------------------------------------- **/

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;



        View view = inflater.inflate(R.layout.fragment_manage_current, container, false);


        String [] dataPickup = {"pickup", "pickup2", "pickup3", "pickup4", "pickup5", "pickup2", "pickup2", "pickup2", "pickup2", "pickup2"};
        String [] dataLeague = {"League", "League2", "League3", "League4", "League5", "League2", "League2", "League2", "League2", "League2"};


        if(pickup){

            ListView listView = (ListView) view.findViewById(R.id.listView);
            //    ArrayAdapter adp = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, dataPickup);
            //   listView.setAdapter(adp);

            ArrayList<FindEventData> arrayListData = new ArrayList<FindEventData>();

            FindEventData exampleData = new FindEventData("Soccer","Alexander Zidros", "4:55pm", "Ravina Park", "8 People Attending", R.drawable.srre, "43.659159", "-79.4725638");
            FindEventData exampleDataTwo = new FindEventData("Squash","Alexander Bramwell", "1:25pm", " High Park Fitness", "2 People Attending", R.drawable.srre, "43.659159", "-79.4725638");

            arrayListData.add(exampleDataTwo);

            for (int x = 0; x < 10; x++) {

                arrayListData.add(new FindEventData("Soccer","Alexander Zidros", "4:55pm", "Ravina Park", "8 People Attending", R.drawable.srre, "43.659159", "-79.4725638"));

            }


            arrayListData.add(exampleData);
            arrayListData.add(exampleDataTwo);

            FindEventAdapter adapter = new FindEventAdapter(getContext(),arrayListData);

            listView.setAdapter(adapter);




        }


        if (league){

            ListView listView = (ListView) view.findViewById(R.id.listView);

            ArrayList<HostMyLeagueListViewData> data = new ArrayList<HostMyLeagueListViewData>();

            data.add(new HostMyLeagueListViewData("Joojee League", "255,154,45,86", "255,255,0,0", "255,0,0,255", "Meme Team", "The Jooj's", "4:16pm", "March 26th, 2016"));

            MyLeagueHostListViewAdapter adapter = new MyLeagueHostListViewAdapter(getContext(), data);

            listView.setAdapter(adapter);


        }





        //  Button clickBtn = (Button) view.findViewById(R.id.clickBtn1);






        //    clickBtn.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View v) {

         /*   if(v.getId() == R.id.clickBtn1){

                Toast.makeText(getContext(), "wtf the FIRST worked", Toast.LENGTH_LONG).show();
               // ViewGroup vg = (ViewGroup) getView();
             //   vg.removeAllViews();
             //   vg.addView(view);


            }
    */
    }

}