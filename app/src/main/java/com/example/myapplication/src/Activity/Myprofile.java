package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.user_reponse.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class Myprofile extends AppCompatActivity {
    private TextView txtusername,txtemail,txtsdt,txtage,txtgender;
    private ImageView imgavartaruser,imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        anhxa();
        setData();
    }

    private void setData() {
        User user = new Gson().fromJson(MainActivity.sharedPreferences.getString("user",""),User.class);
        txtusername.setText(user.getName());
        txtsdt.setText(user.getPhone()+"");
        txtemail.setText(user.getEmail());
        Picasso.with(Myprofile.this).load(user.getAvatar()).into(imgavartaruser);
        txtage.setText(user.getAge()+"");
        String sex;
        if(user.getGender() != null){
            if(user.getGender().equals("0")){
                sex = "Male";
            }else{
                sex = "Gir";
            }
        }else{
            sex = "not data";
        }
        txtgender.setText(sex);
    }


    private void anhxa() {
        txtgender = findViewById(R.id.txtgender);
        txtage = findViewById(R.id.txtage);
        imgavartaruser = findViewById(R.id.imgavartaruser);
        txtsdt = findViewById(R.id.txtsdt);
        txtemail = findViewById(R.id.txtemail);
        txtusername = findViewById(R.id.txtusername);
        imgback = findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}