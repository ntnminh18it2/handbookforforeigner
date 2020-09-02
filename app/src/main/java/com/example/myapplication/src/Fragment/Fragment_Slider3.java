package com.example.myapplication.src.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.src.Activity.MainActivity;



public class Fragment_Slider3 extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_slide3,container,false);
        Button btnGetStarTed=view.findViewById(R.id.btnGetStarTed);

        btnGetStarTed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }

}
