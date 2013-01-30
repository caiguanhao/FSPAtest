package com.example.fspa_test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;

public class Main extends FragmentActivity {

    private static CustomAdapter adapter;
    private static ViewPager pager;
    private static TabPageIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);

        indicator = (TabPageIndicator) findViewById(R.id.indicator);

        adapter = new CustomAdapter(this, pager, indicator);

        adapter.addTab(RandomStringFragment.class, null, "PAGE 1");
        adapter.addTab(TestFragment.class, null, "PAGE 2");
        adapter.addTab(TestFragment.class, null, "PAGE 3");

        adapter.update(0);
    }

}