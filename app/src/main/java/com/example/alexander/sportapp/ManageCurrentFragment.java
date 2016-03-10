package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Element;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexander.sportapp.FindEventAdapter;
import com.example.alexander.sportapp.FindEventData;
import com.example.alexander.sportapp.HostMyLeagueListViewData;
import com.example.alexander.sportapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by alexa on 3/1/2016.
 */
public class ManageCurrentFragment extends Fragment implements View.OnClickListener {

    public ManageCurrentFragment() {
        // Required empty public constructor

    }

    TextView daText;
    TextView daText2;

     ListView listView;

    LayoutInflater inflater;
    ViewGroup container;

    ArrayList<HostMyLeagueListViewData> ListViewDataParent = new ArrayList<HostMyLeagueListViewData>();

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
/*
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
*/
        }

        if (league){

            SharedPreferences userClientInfo = getActivity().getSharedPreferences("StoredActiveUserDate", getContext().MODE_PRIVATE);

            listView = (ListView) view.findViewById(R.id.listView);

            ArrayList<HostMyLeagueListViewData> data = new ArrayList<HostMyLeagueListViewData>();

           // data.add(new HostMyLeagueListViewData("Joojee League", "255,000,255,0", "255,255,0,0", "255,0,0,255", "Meme Team", "The Jooj's", "4:16pm", "Wednesday, March 26th, 2016", "", "","","",""));

            getMyHostedLeagues(userClientInfo.getString("username", "noValue"));



        }


        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View v) {
    }




    /**
     *
     *
     *  GET LISTVIEW DATA STUFF BELOW
     *
     *
     * */


    public void getMyHostedLeagues(String hostusername) {

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

                hm.put("hostusername", params[0]);


                return rc.sendPostRequest("http://zidros.ca/sportappgetmyhostedleagues.php", hm);

            }

            @Override
            protected void onPostExecute(String s){


                boolean one = false;
                boolean two = false;


                ArrayList<HostMyLeagueListViewData> ListViewData = new ArrayList<HostMyLeagueListViewData>();

                /** 6 VALUES */


                // remove later
                boolean start = true;
                boolean getNumberOfElementsPerArray = false;

                String[] elementValues = new String[21];
                int elementIterator = 0;

                boolean inBetween = false;

                String temp = "";

                String NumberOfElementsPerArray = "";

                boolean collect = false;
                String yo = "";
                     for (int x = 0; x < (s.length()-1); x++){


                         if( s.charAt(x) == '%' && !two && inBetween) {
                             elementValues[elementIterator] = temp;

                          //    String[] deleteTempElementValues = {elementValues[0], "255,000,255,0", "255,255,0,0", "255,0,0,255", "Meme Team", "The Jooj's", "4:16pm", "WEDNESDAY", elementValues[1], elementValues[2], elementValues[3], elementValues[4], elementValues[5]};
                           //    ListViewData.add(new HostMyLeagueListViewData(deleteTempElementValues));
                             ListViewData.add(new HostMyLeagueListViewData(elementValues[0], "255,000,255,0", "255,255,0,0", "255,0,0,255", "Meme Team", "The Jooj's", "4:16pm", "WEDNESDAY", elementValues[1], elementValues[2], elementValues[3], elementValues[4], elementValues[5] ));

                             temp = "";
                             collect = false;
                             one = true;
                             elementIterator = 0;

                         } else if (s.charAt(x) == '%' && !two && !start && !inBetween){
                             if (NumberOfElementsPerArray.length() > 0){
                                 getNumberOfElementsPerArray = false;
                                 elementValues = new String[Integer.parseInt(NumberOfElementsPerArray)];
                                 NumberOfElementsPerArray = "";

                             }

                              collect = false;
                              one = true;

                             // remove later
                             getNumberOfElementsPerArray = false;


                             // remove later
                         } else if (getNumberOfElementsPerArray) {

                             NumberOfElementsPerArray += s.charAt(x);

                         }

                        // remove later
                         if (s.charAt(x) == '%' && start){

                             start = false;
                             getNumberOfElementsPerArray = true;
                         }

                         if (s.charAt(x) == 'I' && one){
                             one = false;
                             two = true;
                         }

                         if (s.charAt(x) == '%' && two){

                             inBetween = true;
                             two = false;
                             collect = true;


                         } else if (collect) {

                             if (s.charAt(x) == ','){

                                 elementValues[elementIterator] = temp;
                                 temp = "";
                                 elementIterator++;

                             } else {

                                 temp += s.charAt(x);

                             }



                         }

                     }

            String da = "";

/**
                da += ListViewData.get(6).LeagueName;
                da += ", ";
                da += ListViewData.get(6).RankSystem;
                da += ", ";
                da += ListViewData.get(6).Sport;
                da += ", ";
                da += ListViewData.get(6).Leaguetype;
                da += ", ";
                da += ListViewData.get(6).Private;
                da += ", ";
                da += ListViewData.get(6).HostUserName;

**/

                ListViewDataParent = ListViewData;


             //   ArrayList<HostMyLeagueListViewData> data = new ArrayList<HostMyLeagueListViewData>();

            //    data.add(new HostMyLeagueListViewData("Joojee League", "255,000,255,0", "255,255,0,0", "255,0,0,255", "Meme Team", "The Jooj's", "4:16pm", "Wednesday, March 26th, 2016", "", "","","",""));
//
                MyLeagueHostListViewAdapter adapter = new MyLeagueHostListViewAdapter(getContext(), ListViewData);


                listView.setAdapter(adapter);

         //       da = Integer.toString(elementValues.length);


                //da +=

/*
                for (int r = 0; r < ListViewData.size(); r++){

                    da +=  ListViewData.get(r).MatchDate;
                    da += ",";

                }
                */
               // daText2.setText(elementValues[5]);
           /**     daText2.setText(da); */
              //  daText2.setText(Integer.toString(elementValues.length));
               // daText2.setText(NumberOfElementsPerArray);
               // daText.setText(s);
                if ((s.charAt(0) == 'S') && (s.charAt(1) == 'u')){


                    //     Intent intent = new Intent(HostLeague_CreateOne.this, ManageMyPickups.class);
                    //    startActivity(intent);

                }

            }
        }
        new UserLoginClass().execute(hostusername);

    }





}