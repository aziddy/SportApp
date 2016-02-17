package com.example.alexander.sportapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        Calendar cal = Calendar.getInstance();

        int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);



        return new TimePickerDialog(getActivity(), this, hourOfDay, minute, false);


    }


    /**
     * TODO might have to revise code for sending date to activity from dialog. Current Method might take too much RAM
     */

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        SharedPreferences settings = getActivity().getSharedPreferences("passActivityToDatePickerData", getActivity().MODE_PRIVATE);
        String ActivityName = settings.getString("ActivityName", "");


        /** == "Host_PickTime" */
        if ('H' == ActivityName.charAt(0) && 'o' == ActivityName.charAt(1) && 's' == ActivityName.charAt(2) && 't' == ActivityName.charAt(3)
                && 'e' == ActivityName.charAt(12) && 'm' == ActivityName.charAt(11) && 'i' == ActivityName.charAt(10) && 'T' == ActivityName.charAt(9)) {


            Host_PickDateTime connectedActivity = (Host_PickDateTime) getActivity();
          //  connectedActivity.dialogTimeToActivity(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
           //  formatTime(hourOfDay, minute)

            connectedActivity.dialogTimeToActivity(formatTime(hourOfDay,minute));

        }


    }

    private static String formatTime(int hourOfDay, int minute) {

        String value = "No Value";
        String minuteString = Integer.toString(minute);


        if (hourOfDay >= 12 && hourOfDay < 13) {
            if (Integer.toString(minute).length() == 1){
                minuteString = "";
                minuteString  = minuteString + "0" + Integer.toString(minute);
            }
            value = Integer.toString(hourOfDay) + ":" + minuteString + "AM";
        }
        
        if (hourOfDay < 12){
            if (Integer.toString(minute).length() == 1){
                minuteString = "";
                minuteString  = minuteString + "0" + Integer.toString(minute);
            }
            value = Integer.toString(hourOfDay) + ":" + minuteString + "AM";
        }

        if (hourOfDay >= 13){
            if (Integer.toString(minute).length() == 1){
                minuteString = "";
                minuteString  = minuteString + "0" + Integer.toString(minute);
            }
            hourOfDay = hourOfDay - 12;
            value = Integer.toString(hourOfDay) + ":" + minuteString + "PM";
        }

        return value;
    }
}