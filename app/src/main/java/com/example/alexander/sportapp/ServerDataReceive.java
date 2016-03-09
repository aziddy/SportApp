package com.example.alexander.sportapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alex on 3/7/2016.
 */
public class ServerDataReceive {


    String URL = "";
    String dataType = "";
    ArrayList arrayList = new ArrayList();

    private ArrayList RESULT = new ArrayList();
//    private String RESULT = "";

    ServerDataReceive(ArrayList<String[]> arrayDataToPutInDataType, String dataType, String URL) {

        this.URL = URL;
        this.dataType = dataType;

    }






    public void AsyncGetServerData(String... put) {
 //   public void AsyncGetServerData(String hostusername) {

        class AsyncGetServerDataClass extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute(){
                //     loading = ProgressDialog.show(HostLeague_CreateOne.this, "WAIT", null, true, true);
            }

            @Override
            protected String doInBackground(String[] put2){

                RegisterUserClass rc = new RegisterUserClass();

                HashMap<String,String> hm = new HashMap<String, String>();

                hm.put("hostusername", put2[0]);


                return rc.sendPostRequest(URL, hm);

            }

            @Override
            protected void onPostExecute(String s){

                ArrayList<String[]> StringArrayTempData = new ArrayList<>();



                boolean one = false;
                boolean two = false;
                int LeagueIterator = -1;
                int ElementIterator = -1;

                ArrayList<String[]> ListViewData = new ArrayList<>();

               // ArrayList<String[]>[][] ListViewData = new ArrayList[0][0];


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

                    // put elements into bigger array
                    if( s.charAt(x) == '%' && !two && inBetween) {

                        elementValues[elementIterator] = temp;

                        StringArrayTempData.add(elementValues);


                     //   putDataIn(StringArrayTempData, dataType);




                        temp = "";
                        collect = false;
                        one = true;
                        elementIterator = 0;

                    } else if (s.charAt(x) == '%' && !two && !start && !inBetween){
                        // elementIterator = 0;
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
                        LeagueIterator++;

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
                putDataIn(StringArrayTempData, dataType);
           //     RESULT = "a";
/*
                da += ListViewData.get(0).LeagueName;
                da += ", ";
                da += ListViewData.get(0).RankSystem;
                da += ", ";
                da += ListViewData.get(0).Sport;
                da += ", ";
                da += ListViewData.get(0).Leaguetype;
                da += ", ";
                da += ListViewData.get(0).Private;
                da += ", ";
                da += ListViewData.get(0).HostUserName;
*/

                //       da = Integer.toString(elementValues.length);


                //da +=

/*
                for (int r = 0; r < ListViewData.size(); r++){

                    da +=  ListViewData.get(r).MatchDate;
                    da += ",";

                }
                */
                // daText2.setText(elementValues[5]);
          //      daText2.setText(da);
                //  daText2.setText(Integer.toString(elementValues.length));
                // daText2.setText(NumberOfElementsPerArray);
                // daText.setText(s);
                if ((s.charAt(0) == 'S') && (s.charAt(1) == 'u')){


                    //     Intent intent = new Intent(HostLeague_CreateOne.this, ManageMyPickups.class);
                    //    startActivity(intent);

                }

            }
        }



        new AsyncGetServerDataClass().execute(put);

    }



      public void putDataIn(ArrayList<String[]> elementValuesArray, String dataTypeIn){

          if (dataTypeIn == "HostMyLeagueListViewData"){


              for (int x = 0; x < (elementValuesArray.size()-1); x++) {

                  for (int y = 0; y < (elementValuesArray.get(x).length-1); x++){

                  }

              }


          }



      }



}
