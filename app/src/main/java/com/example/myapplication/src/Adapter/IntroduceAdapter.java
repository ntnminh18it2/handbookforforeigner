package com.example.myapplication.src.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class IntroduceAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>arrayFragment=new ArrayList<>();
    private ArrayList<String>arrayTitle=new ArrayList<>();

    public IntroduceAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment, String title){
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}
