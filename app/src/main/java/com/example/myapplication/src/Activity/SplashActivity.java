package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;
import com.example.myapplication.src.Activity.IntroduceActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        Handler handler = new Handler();
            Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), IntroduceActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
                finish();
            }
        };
        handler.postDelayed(runnable,2000);
    }
}
