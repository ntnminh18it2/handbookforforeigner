package com.example.myapplication.util.listener_change_edittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.myapplication.util.constants.Constants;
import com.example.myapplication.util.validations.Validations;

public class addListenerOnTextChange implements TextWatcher {
    private Context mContext;
    private int mIndex;
    private EditText editText;

    public addListenerOnTextChange(Context context, int index,EditText editText) {
        super();
        this.mContext = context;
        this.mIndex = index;
        this.editText = editText;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        switch (mIndex){
            case 1: // check email
                if(!Validations.isEmail(editText.getText().toString())){
                    editText.setError(Constants.erroEmail);
                }else{
                    editText.setError(null);
                }
                break;
            case 2:
                if(!Validations.isPass(editText.getText().toString())){
                    editText.setError(Constants.erroPass);
                }else {
                    editText.setError(null);
                }
                break;
            case 3:
                if(!Validations.isName(editText.getText().toString())){
                    editText.setError(Constants.erroUsername);
                }else{
                    editText.setError(null);
                }
                break;
        }
    }
}