package com.example.alexander.sportapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by alexander on 1/12/2016.
 */
public class RegisterUserClass {

    public String sendPostRequest(String requestURL, HashMap<String, String> postDataParams) {

        String phpEcho = "";

        try {

            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader( new InputStreamReader(con.getInputStream()));
                //phpEcho  = br.readLine();


                String line = "";


                while((line = br.readLine()) != null){
                    phpEcho = phpEcho + line;
                }

            }




        } catch (MalformedURLException e){



        } catch (IOException e){


        }


        if (phpEcho.length() < 3){
            phpEcho = "Connection Error";
        }
        return phpEcho;

    }

    String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        Boolean first = true;


        for (Map.Entry<String, String> entry : params.entrySet()) {




            if (first) {

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                Log.d("WTF", "/WTF  " + result.toString());
                first = false;

            } else {
                result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                Log.d("WTF","/WTF  "+result.toString());


            }
        }

        Log.d("WTF","/WTF  "+result.toString());
        return result.toString();
    }


}