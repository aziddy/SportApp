package com.example.alexander.sportapp;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.Toast;

import layout.PickupCompletedFragment;
import layout.ScreenSlidePageFragment;



public class ManageMyEvents extends FragmentActivity{

Boolean pickupSelected = true;
    //  omfg

     ViewPager vPager;

    PagerAdapter vPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_events);
        SharedPreferences sf;



        TabLayout tl = (TabLayout) findViewById(R.id.tab_layout);
        tl.addTab(tl.newTab().setText("Current"));
        tl.addTab(tl.newTab().setText("Completed"));
        tl.setTabGravity(TabLayout.GRAVITY_FILL);

        final Button PickupBtn = (Button) findViewById(R.id.PickupBtn);
        final Button LeagueBtn = (Button) findViewById(R.id.LeagueBtn);

        PickupBtn.setTypeface(null, Typeface.BOLD);
        LeagueBtn.setTypeface(null, Typeface.NORMAL);

        PickupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickupSelected = true;
                LeagueBtn.setBackgroundColor(Color.argb(255, 247, 10, 137));
                PickupBtn.setBackgroundColor(Color.argb(255, 206, 12, 116));
                PickupBtn.setTypeface(null, Typeface.BOLD);
                LeagueBtn.setTypeface(null, Typeface.NORMAL);



            }
        });





        LeagueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickupSelected = false;
                PickupBtn.setBackgroundColor(Color.argb(255, 247, 10, 137));
                LeagueBtn.setBackgroundColor(Color.argb(255, 206, 12, 116));
                PickupBtn.setTypeface(null, Typeface.NORMAL);
                LeagueBtn.setTypeface(null, Typeface.BOLD);

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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (vPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            vPager.setCurrentItem(vPager.getCurrentItem() - 1);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Toast.makeText(ManageMyEvents.this, Integer.toString(position), Toast.LENGTH_SHORT).show();



            if ( false == pickupSelected){
                if (position == 1){
                    return new ScreenSlidePageFragment();
                } else {
                    return new PickupCompletedFragment();
                }

            } else {

                if (position == 1){
                    return new PickupCompletedFragment();
                } else {
                    return new ScreenSlidePageFragment();

                }

            }




        }






        @Override
        public int getCount() {
            return 2;
        }
    }

}
