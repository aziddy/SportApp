package com.example.alexander.sportapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import layout.ManageCompletedFragment;

public class ManageMyLeaguesMatches extends AppCompatActivity {

    Boolean pickupSelected = true;
    //  omfg

    Fragment frag0;
    Fragment frag1;

    ViewPager vPager;

    PagerAdapter vPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_manage_my_leagues_matches);
        SharedPreferences sf = getSharedPreferences("ManageMyEventsActivityFragmentData", MODE_PRIVATE);
        SharedPreferences.Editor sfedit = sf.edit();




        TabLayout tl = (TabLayout) findViewById(R.id.tab_layout);
        tl.addTab(tl.newTab().setText("Scheduled"));
        tl.addTab(tl.newTab().setText("Completed"));
        tl.setTabGravity(TabLayout.GRAVITY_FILL);

        final Button MatchesBtn = (Button) findViewById(R.id.MatchesBtn);
        final Button TeamsBtn = (Button) findViewById(R.id.TeamsBtn);

        MatchesBtn.setBackgroundColor(Color.argb(255, 206, 12, 116)); /** darker **/
        TeamsBtn.setBackgroundColor(Color.argb(255, 247, 10, 137));


        MatchesBtn.setTypeface(null, Typeface.BOLD);
        TeamsBtn.setTypeface(null, Typeface.NORMAL);
       // MatchesBtn.setTypeface(null, Typeface.NORMAL);
       // TeamsBtn.setTypeface(null, Typeface.BOLD);


        MatchesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickupSelected = true;


            }
        });





        TeamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickupSelected = false;/*
                MatchesBtn.setBackgroundColor(Color.argb(255, 247, 10, 137));
                TeamsBtn.setBackgroundColor(Color.argb(255, 206, 12, 116));
                MatchesBtn.setTypeface(null, Typeface.NORMAL);
                TeamsBtn.setTypeface(null, Typeface.BOLD); */
                Intent intent = new Intent(getApplication(),ManageMyLeaguesTeams.class);
                startActivity(intent);

            }
        });

        vPager = (ViewPager) findViewById(R.id.pager);
        vPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        vPager.setAdapter(vPagerAdapter);


        vPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
        tl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    @Override
    public void onBackPressed() {
        if (vPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.

        } else {
            // Otherwise, select the previous step.
            //vPager.setCurrentItem(vPager.getCurrentItem() - 1);
        }

        super.onBackPressed();
        overridePendingTransition(0, 0);



    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //    Toast.makeText(ManageMyLeagues.this, Integer.toString(position), Toast.LENGTH_SHORT).show();



            if ( false == pickupSelected){
                if (position == 1){

                    frag1 = new ManageCompletedFragment();
                    return frag1;

                } else {
                    frag0 = new ManageCurrentFragment();
                    return frag0;
                }

            } else {

                if (position == 1){
                    frag1 = new ManageCompletedFragment();
                    return frag1;

                } else {
                    frag0 = new ManageCurrentFragment();
                    return frag0;
                }
            }

        }


        class LoadNextWithoutLag extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute(){

            }

            @Override
            protected String doInBackground(String... params){
                //  Intent intent = new Intent(, )

                //   return "21";
                return "21";
            }

            @Override
            protected void onPostExecute(String s){
                //    pd.dismiss();


            }

        }


        @Override
        public int getCount() {
            return 2;
        }
    }
}

