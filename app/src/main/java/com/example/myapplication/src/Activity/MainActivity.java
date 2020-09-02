package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.src.Adapter.AdapterViewPagerManactivity;
import com.example.myapplication.src.Fragment.Account_Fragment;
import com.example.myapplication.src.Fragment.home_fragment.HomePage_Fragment;
import com.example.myapplication.src.Fragment.Notification_Fragment;
import com.example.myapplication.src.Fragment.Search_Fragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewpager;
    private TabLayout mTabLayout;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    static public boolean checkUserAcount(){
        if(sharedPreferences.getString("username","") != null &&
                sharedPreferences.getString("password","") != null &&
                sharedPreferences.getInt("idUser",0) != 0){
            return true;
        }else{
            return false;
        }
    }


    private void init() {
        sharedPreferences = getSharedPreferences("datalogin",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        mViewpager = findViewById(R.id.viewPagerMain);
        mTabLayout = findViewById(R.id.tabLayoutMain);
        AdapterViewPagerManactivity adapter = new AdapterViewPagerManactivity(getSupportFragmentManager());
        adapter.addFragment(new HomePage_Fragment(),"Home");
        adapter.addFragment(new Search_Fragment(),"Search");
        adapter.addFragment(new Notification_Fragment(),"Notification");
        adapter.addFragment(new Account_Fragment(),"Acount");

        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);//
        mTabLayout.getTabAt(0).setIcon(R.drawable.home);
        mTabLayout.getTabAt(1).setIcon(R.drawable.search);
        mTabLayout.getTabAt(2).setIcon(R.drawable.notification);
        mTabLayout.getTabAt(3).setIcon(R.drawable.user);
    }


}
