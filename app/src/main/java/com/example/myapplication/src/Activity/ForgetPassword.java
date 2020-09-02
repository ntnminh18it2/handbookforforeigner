package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.example.myapplication.services.JavaMailAPI;
import com.example.myapplication.src.dialog.LoadingDialog_new;
import com.example.myapplication.util.validations.Validations;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity {
    private Button btnSend;
    private TextView txtEmailUser;
    private ImageView imgback;
    private EditText editTextCode;
    int number = 0;
    String email = "";
    private String TAG = "AAA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        init();
        Intent intent = getIntent();
        if (intent.hasExtra("email")) {
            email = intent.getStringExtra("email");
            listennerClicked(email);
            txtEmailUser = findViewById(R.id.txtEmail);
            txtEmailUser.setText(email);

            Random random = new Random();
            number = random.nextInt(100000);
            Toast.makeText(this, "Please check your email", Toast.LENGTH_SHORT).show();
            JavaMailAPI javaMailAPI = new JavaMailAPI(this, email, "verify account", number + "");
            javaMailAPI.execute();
        }
    }
    private void init() {
        btnSend = findViewById(R.id.btnSend);
        txtEmailUser = findViewById(R.id.txtEmail);
        imgback = findViewById(R.id.imgback);
        editTextCode = findViewById(R.id.editTextCode);
    }


    private void listennerClicked(final String finalEmail) {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextCode.getText().toString().equals(number + "")) {
                    showDiaLog();
                } else {
                    Toast.makeText(ForgetPassword.this, "code is not correct ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showDiaLog() {
        final Dialog dialog = new Dialog(ForgetPassword.this);
        dialog.setContentView(R.layout.layout_dialog_confirm_pass);

        final EditText txtNewPass = dialog.findViewById(R.id.txtNewPass);
        final EditText txtConfirmPass = dialog.findViewById(R.id.txtConfirmPass);
        LinearLayout linearLayoutLogin = dialog.findViewById(R.id.linearLayoutLogin);

        linearLayoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validations.isPassAndConfirmPass(txtNewPass.getText().toString(), txtConfirmPass.getText().toString())) {

                    final Dialog dialog1 = LoadingDialog_new.showdialog(ForgetPassword.this);
                    dialog1.show();

                    DataService dataService = APIServices.getService();
                    Call<String> call = dataService.updatePass(email, txtNewPass.getText().toString());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d(TAG, "onResponse: updatePass-" + response.toString());
                            if (response.isSuccessful()) {
                                if (response.body().equals("Update Err")) {
                                    dialog.dismiss();
                                    Toast.makeText(ForgetPassword.this, response.body(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgetPassword.this, "Susscess", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                    finish();
                                }

                            } else {
                                Toast.makeText(ForgetPassword.this, "update failed", Toast.LENGTH_SHORT).show();
                                dialog1.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(ForgetPassword.this, "please try again", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                        }
                    });
                } else {
                    Toast.makeText(ForgetPassword.this, "Data invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}