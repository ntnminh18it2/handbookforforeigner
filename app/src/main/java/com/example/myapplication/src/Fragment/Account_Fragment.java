package com.example.myapplication.src.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.src.dialog.LoadingDialog_new;
import com.example.myapplication.src.Activity.HelpsSuport;
import com.example.myapplication.src.Activity.LoginActivity;
import com.example.myapplication.src.Activity.MainActivity;
import com.example.myapplication.src.Activity.Myprofile;
import com.example.myapplication.util.constants.Constants;

public class Account_Fragment extends Fragment {
    private View mView;
    private RelativeLayout relativeAcountMyprofile,relivelayouhelps_supporrt,relivelayoutabout,relivelayoutlogout;
    private TextView txttenuser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.account_fragment,container,false);
        init();
        if(MainActivity.checkUserAcount()){
            listenerOnclicked();
        }else{
            startActivity(new Intent(getContext(),LoginActivity.class));
        }
        return  mView;
    }

    private void listenerOnclicked() {
        relativeAcountMyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mView.getContext(), Myprofile.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
            }
        });
        relivelayouhelps_supporrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
                startActivity(new Intent(getActivity(), HelpsSuport.class));
            }
        });
        relivelayoutabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingDialog_new.showDialogAcount(getActivity(),"About", Constants.mAbout);
            }
        });
        relivelayoutlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.editor.clear();
                MainActivity.editor.commit();
                startActivity(new Intent(getContext(),LoginActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
            }
        });
    }

    private void init() {
        txttenuser = mView.findViewById(R.id.txttenuser);
        String nameUser = MainActivity.sharedPreferences.getString("username","");
        txttenuser.setText(nameUser);

        relivelayoutlogout = mView.findViewById(R.id.relivelayoutlogout);
        relivelayoutabout = mView.findViewById(R.id.relivelayoutabout);
        relivelayouhelps_supporrt = mView.findViewById(R.id.relivelayouhelps_supporrt);
        relativeAcountMyprofile = mView.findViewById(R.id.relativeAcountMyprofile);
    }
}
