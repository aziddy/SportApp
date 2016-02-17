package com.example.alexander.sportapp;


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
import android.widget.Toast;

import layout.ScreenSlidePageFragment;

public class ManageMyEvents extends FragmentActivity{


    //  omfg

     ViewPager vPager;

    PagerAdapter vPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TabLayout tl = (TabLayout) findViewById(R.id.tab_layout);
        tl.addTab(tl.newTab().setText("Current"));
        tl.addTab(tl.newTab().setText("Completed"));
        tl.setTabGravity(TabLayout.GRAVITY_FILL);


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
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
