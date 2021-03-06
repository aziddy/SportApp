package com.example.alexander.sportapp;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


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
    ArrayList<TeamLeagueListViewData> ListViewDataParentTwo = new ArrayList<TeamLeagueListViewData>();


    String activityName = "";
    Boolean pickup = false;
    Boolean league = false;
    Boolean ManageMyLeaguesTeams = false;
    Boolean LeagueMatch = false;


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


                /**  ADD Activities here  */

                if (activityName.equals("ManageMyPickups")) {
                    pickup = true;
                } else if (activityName.equals("ManageMyLeagues")) {
                    league = true;
                } else if (activityName.equals("ManageMyLeaguesTeams")){
                    ManageMyLeaguesTeams = true;
                } else if (activityName.equals("ManageMyLeaguesMatches")){
                    LeagueMatch = true;
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

        if(ManageMyLeaguesTeams){


            ListView.LayoutParams lvlp = new ListView.LayoutParams(1,1);

            listView = (ListView) view.findViewById(R.id.listView);

          //  listView.setLayoutParams(lvlp);



/*
            FrameLayout fl = new FrameLayout(getContext());
            fl.setBackground(getResources().getDrawable(R.drawable.oval_green));
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(200, 200);
            fl.setLayoutParams(flp);
*/



           FrameLayout fl = (FrameLayout) view.findViewById(R.id.bottomRightFrame);
         //   ll.setBackgroundColor(Color.argb(255,255,0,0));


         //   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

         //   ImageView iv = new ImageView(getContext());
         //   iv.setImageResource(R.drawable.oval_green);

               fl.setBackground(getResources().getDrawable(R.drawable.cross));

      fl.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(getContext(), "woah", Toast.LENGTH_SHORT).show();
              Intent intent = new Intent(getActivity(), CreateNewTeamLeague.class);
              startActivity(intent);
          }
      });

          //  Button btn = new Button(getContext());


        //    btn.setLayoutParams(params);

          //  btn.setLayoutParams(new LayoutParams());


          //  ll.addView(btn);


          //  Toast.makeText(getContext(), "WORDKED", Toast.LENGTH_SHORT).show();

        }

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

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                      //  Toast.makeText(getActivity(), ListViewDataParent.get(position).HostUserName, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ManageMyLeaguesMatches.class);
                    startActivity(intent);
                    SharedPreferences sf = getActivity().getSharedPreferences("CurrentLeagueSelected", getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor sfe = sf.edit();

                    sfe.putString("CurrentLeague", ListViewDataParent.get(position).LeagueName);
                    sfe.apply();
                  //  Toast.makeText(getContext(), sf.getString("CurrentLeague", "no workerino"), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), sf.getString("CurrentLeague", "no workerino"), Toast.LENGTH_SHORT).show();

                }
            });

        }



        if (LeagueMatch){

            SharedPreferences userClientInfo = getActivity().getSharedPreferences("StoredActiveUserDate", getContext().MODE_PRIVATE);
            SharedPreferences sf = getActivity().getSharedPreferences("CurrentLeagueSelected", getActivity().MODE_PRIVATE);

       //  Toast.makeText(getContext(), "MATCH",Toast.LENGTH_SHORT).show();
            listView = (ListView) view.findViewById(R.id.listView);




        }



        if (ManageMyLeaguesTeams){

            SharedPreferences userClientInfo = getActivity().getSharedPreferences("StoredActiveUserDate", getContext().MODE_PRIVATE);
            SharedPreferences sf = getActivity().getSharedPreferences("CurrentLeagueSelected", getActivity().MODE_PRIVATE);

            listView = (ListView) view.findViewById(R.id.listView);

            String LeagueName = "a";

            getMyLeagueTeam(sf.getString("CurrentLeague", "no value"));


            ArrayList<TeamLeagueListViewData> data = new ArrayList<TeamLeagueListViewData>();
        //    data.add(new TeamLeagueListViewData("The Warp", "255-255-255-0","a","95","37","ziddy,testers,bagel","(a)W2L3R3,(b)W1L7R8","ziddy"));
         //   data.add(new TeamLeagueListViewData("The Warp", "255-255-0-0", "a", "80", "17", "ziddy,testers,bagel", "(a)W7L2R1", "ziddy"));
          //  data.add(new TeamLeagueListViewData("The Warp", "255-255-0-255", "a", "60", "7", "ziddy,testers,bagel", "(b)W1L7R8,(a)W7L2R1", "ziddy"));



        //    TeamLeagueListViewAdapter adapter = new TeamLeagueListViewAdapter(getContext(), data);
          //  listView.setAdapter(adapter);





                  /*  SharedPreferences sf = getActivity().getSharedPreferences("DataToLeagueTeamDetail", getContext().MODE_PRIVATE);

                    Toast.makeText(getContext(), "woah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), TeamLeagueDetail.class);
                    startActivity(intent); */


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(), Integer.toString(position),Toast.LENGTH_SHORT).show();

                    SharedPreferences sf = getActivity().getSharedPreferences("DataToLeagueTeamDetail", getContext().MODE_PRIVATE);
                    SharedPreferences.Editor sfe = sf.edit();

                    TeamLeagueListViewData idfk = ListViewDataParentTwo.get(position);

                    sfe.putString("TeamName",idfk.TeamName);
                    sfe.putString("TeamColor",idfk.TeamColor);
                    sfe.putString("Players",idfk.Players);
                    sfe.putString("Wins",idfk.Wins);
                    sfe.putString("Losses",idfk.Losses);
                    sfe.putString("Creator",idfk.Creator);
                    sfe.putString("Rank",idfk.Rank);
                    sfe.putString("CurrentLeague",idfk.CurrentLeague);
                    //sfe.putString("",idfk.)
                    sfe.apply();



                    //Toast.makeText(getContext(), "woah", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), TeamLeagueDetail.class);
                    startActivity(intent);

                }
            });


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
                    loading = ProgressDialog.show(getActivity(), "WAIT", null, true, true);
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

                ListViewDataParent = ListViewData;

                MyLeagueHostListViewAdapter adapter = new MyLeagueHostListViewAdapter(getContext(), ListViewData);

                listView.setAdapter(adapter);

                loading.dismiss();

            }
        }
        new UserLoginClass().execute(hostusername);

    }


    public void getMyLeagueTeam(String currentLeague) {

        class UserLoginClass extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute(){
                loading = ProgressDialog.show(getActivity(), "WAIT", null, true, true);
            }

            @Override
            protected String doInBackground(String... params){

                RegisterUserClass rc = new RegisterUserClass();

                HashMap<String,String> hm = new HashMap<String, String>();

                //hm.put("hostusername", params[0]);
                  hm.put("currentleague", params[0]);
             //     hm.put("creator", params[1]);

                return rc.sendPostRequest("http://zidros.ca/sportappgetleagueteams.php", hm);

            }

            @Override
            protected void onPostExecute(String s){


                boolean one = false;
                boolean two = false;

              //  Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();

                ArrayList<TeamLeagueListViewData> ListViewData = new ArrayList<TeamLeagueListViewData>();

                /** 10 VALUES */


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

                        ListViewData.add(new TeamLeagueListViewData(elementValues[0],elementValues[1],elementValues[2],elementValues[3],elementValues[4],elementValues[5],elementValues[6],elementValues[8] ));

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

                ListViewDataParentTwo = ListViewData;

                TeamLeagueListViewAdapter adapter = new TeamLeagueListViewAdapter(getContext(), ListViewData);

                listView.setAdapter(adapter);

                loading.dismiss();

            }
        }
        new UserLoginClass().execute(currentLeague);

    }


}