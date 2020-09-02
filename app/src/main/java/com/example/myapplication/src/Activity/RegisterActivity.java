package com.example.myapplication.src.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.src.dialog.LoadingDialog;
import com.example.myapplication.util.listener_change_edittext.addListenerOnTextChange;
import com.example.myapplication.util.validations.Validations;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextUserName,editTextPassword,editTextEmail;
    private TextView txtRollback;
    private Button btnRegister;
    private LoadingDialog loadingDialog = new LoadingDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        onclicked();
        addtextChangeListener();
    }

    private void addtextChangeListener() {
        editTextUserName.addTextChangedListener(new addListenerOnTextChange(RegisterActivity.this,3,editTextUserName));
        editTextEmail.addTextChangedListener(new addListenerOnTextChange(RegisterActivity.this,1,editTextEmail));
        editTextPassword.addTextChangedListener(new addListenerOnTextChange(RegisterActivity.this,2,editTextPassword));
    }

    private void onclicked() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validations.isPass(editTextPassword.getText().toString()) && Validations.isEmail(editTextEmail.getText().toString())
                && Validations.isName(editTextUserName.getText().toString().trim())){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    loadingDialog.show(fragmentManager,"123");

                    DataService dataService = APIServices.getService();
                    Call<String>callback = dataService.registerAcount(editTextUserName.getText().toString(),
                            editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","Register: "+response.body());
                            if(response.body().equals("Susscess")){
                                Toast.makeText(RegisterActivity.this, "Reister Success", Toast.LENGTH_SHORT).show();
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("AAA","RegisterErr: "+t.toString());
                            Toast.makeText(RegisterActivity.this, "Err Success", Toast.LENGTH_SHORT).show();
                            loadingDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this, "format error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        txtRollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_ride);
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    private void anhxa(){
        txtRollback = findViewById(R.id.txtRollback);
        editTextUserName = findViewById(R.id.edtUserName);
        editTextPassword = findViewById(R.id.edtPassword);
        editTextEmail = findViewById(R.id.edtEmail);
        btnRegister = findViewById(R.id.btnRegister);
    }
}
