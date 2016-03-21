package com.example.alexander.sportapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class UsernameFind extends Activity {

    ArrayList<String> data;
    ArrayAdapter<String> aa;
    AutoCompleteTextView autocompletetextview;
    int count;
    long lastTimeSent = 0;
    ListView listview;
    EditText editText;
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    ArrayList<String> ListViewDataParent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_find);


        listview = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        sp = getSharedPreferences("UsernameFindToTeamCreate", MODE_PRIVATE);
        spe = sp.edit();

        spe.putString("usernamet", "");
        spe.apply();


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (lastTimeSent == 0) {
                    lastTimeSent = System.currentTimeMillis();
                          FindUsername(s.toString());
                } else {

                    if ((System.currentTimeMillis() - lastTimeSent) > 2000) {
                        lastTimeSent = System.currentTimeMillis();
                                 FindUsername(s.toString());
                    }

                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              // Toast.makeText(getApplicationContext(), ListViewDataParent.get(position), Toast.LENGTH_SHORT).show();
                spe.putString("usernamet", ListViewDataParent.get(position));
                spe.putString("usernameFindLast", "1");
                spe.apply();
                finish();
            }
        });

        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void FindUsername(String input) {

        class FindUsernameAsync extends AsyncTask<String, Void, String> {

            //ProgressDialog loading;


            @Override
            protected void onPreExecute(){
            //    loading = ProgressDialog.show(UsernameFind.this, "WAIT", null, true, true);
            }

            @Override
            protected String doInBackground(String... params){

                RegisterUserClass rc = new RegisterUserClass();

                HashMap<String,String> hm = new HashMap<String, String>();

                hm.put("input", params[0]);

                return rc.sendPostRequest("http://zidros.ca/sportappautocompleteusername.php", hm);
                //   return "21";
            }

            @Override
            protected void onPostExecute(String s){
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

                /** BELOW IS DECODE */

                boolean one = false;
                boolean two = false;

                // CHANGE DATA TYPE ----------------------------------------------------------------
                ArrayList<String> ListViewData = new ArrayList<String>();
                // ---------------------------------------------------------------------------------

                /** 2 VALUES */


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
                       // Toast.makeText(getApplicationContext(), elementValues[0], Toast.LENGTH_SHORT).show();
                        // ADD COMPLETE DATA TO LOCAL ARRAY HERE---------elementValues[0]-------------
                        ListViewData.add(elementValues[0]);
                        // ----------------------------------------------------------------------------

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





                if(ListViewData.size() > 0) {


                    ListViewDataParent = ListViewData;
                    ArrayAdapter aalv = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ListViewData);

                    listview.setAdapter(aalv);


                }

              //  loading.dismiss();

            //    for (int x = 0; x < ListViewData.size(); x++){
             //        Toast.makeText(getApplicationContext(), ListViewDataParent.get(x), Toast.LENGTH_LONG).show();
             //   }

               // Toast.makeText(getApplicationContext(), Integer.toString(ListViewData.size()), Toast.LENGTH_LONG).show();
            }
        }
        new FindUsernameAsync().execute(input);

    }




}
