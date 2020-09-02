package com.example.myapplication.src.Fragment.home_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class Overview_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overview_dannang_homepage_fragment,container,false);
        TextView txt1 = view.findViewById(R.id.txt1);
        txt1.setText("Geographical location\n" +
                "the North borders Thua Thien - Hue province, the West and South borders Quang Nam province, the East borders the East Sea, Da Nang is located in the middle of the country, on the North traffic axis.");
        return view;
    }
}
