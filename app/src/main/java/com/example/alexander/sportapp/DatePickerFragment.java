package com.example.alexander.sportapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alexander on 1/16/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {



    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog DPD = new DatePickerDialog(getActivity(), this, year, month, day);

        DPD.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        return DPD;




    }


    /** TODO might have to revise code for sending date to activity from dialog. Current Method might take too much RAM */

    public void onDateSet(DatePicker view, int year, int month, int day){

         view.setMinDate(System.currentTimeMillis() - 1000);

        SharedPreferences settings = getActivity().getSharedPreferences("passActivityToDatePickerData", getActivity().MODE_PRIVATE);
        String ActivityName = settings.getString("ActivityName", "");


        /** == "Host_PickTime" */
        if ('H' == ActivityName.charAt(0) && 'o' == ActivityName.charAt(1) && 's' == ActivityName.charAt(2) && 't' == ActivityName.charAt(3)
            && 'e' == ActivityName.charAt(12)&& 'm' == ActivityName.charAt(11) && 'i' == ActivityName.charAt(10) && 'T' == ActivityName.charAt(9)){


            Host_PickDateTime connectedActivity = (Host_PickDateTime) getActivity();
            connectedActivity.dialogDateToActivity(formatDate(year, month, day));


        }

    }

    private static String formatDate(int year, int month, int day) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, day);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d, yyyy");

        return sdf.format(date);
    }

}
