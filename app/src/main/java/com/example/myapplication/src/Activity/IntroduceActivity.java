package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.src.Adapter.IntroduceAdapter;
import com.example.myapplication.src.Fragment.Fragment_Slider1;
import com.example.myapplication.src.Fragment.Fragment_Slider2;
import com.example.myapplication.src.Fragment.Fragment_Slider3;

public class IntroduceActivity extends AppCompatActivity {
    private ViewPager mainviewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        anhxa();
    }
    private void anhxa() {
        mainviewpager=findViewById(R.id.mainviewpager);
        IntroduceAdapter adapter=new IntroduceAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Slider1(),"fragmentTitle1");
        adapter.addFragment(new Fragment_Slider2(),"fragmentTitle2");
        adapter.addFragment(new Fragment_Slider3(),"fragmentTitle3");
        mainviewpager.setAdapter(adapter);
    }
}