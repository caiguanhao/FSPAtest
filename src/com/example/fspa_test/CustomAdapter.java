package com.example.fspa_test;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;

public class CustomAdapter extends FragmentStatePagerAdapter implements
        ViewPager.OnPageChangeListener {

    private static FragmentActivity mActivity;
    private static ViewPager mViewPager;
    private static TabPageIndicator mIndicator;

    private static ArrayList<SubPageFragment> TABS =
            new ArrayList<SubPageFragment>();

    @SuppressLint("ValidFragment")
    public static final class SubPageFragment {
        private final String NAME;
        private final Class<?> CLASS;
        private Bundle ARGUMENTS;

        SubPageFragment(Class<?> c, Bundle b, String n) {
            CLASS = c;
            NAME = n;
            ARGUMENTS = b;
        }
    }

    public CustomAdapter(FragmentActivity activity, ViewPager pager,
            TabPageIndicator indicator) {
        super(activity.getSupportFragmentManager());
        mActivity = activity;
        mViewPager = pager;
        mIndicator = indicator;
        mViewPager.setAdapter(this);
        mIndicator.setOnPageChangeListener(this);
    }

    public void update(int position) {
        try {
            notifyDataSetChanged();
            mIndicator.notifyDataSetChanged();
        } catch (NullPointerException e) {}
        mViewPager.setAdapter(this);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setCurrentItem(position);
    }

    public void addTab(Class<?> c, Bundle b, String n) {
        TABS.add(new SubPageFragment(c, b, n));
    }

    public void removeTab(int p) {
        TABS.remove(p);
    }

    @Override
    public Fragment getItem(int position) {
        SubPageFragment frag = TABS.get(position);
        return Fragment.instantiate(mActivity, frag.CLASS.getName(),
                frag.ARGUMENTS);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TABS.get(position).NAME;
    }

    @Override
    public int getCount() {
        return TABS.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
            int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}