package com.example.myapplication.src.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.example.myapplication.src.Activity.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluateDialog extends DialogFragment {
    private View view;
    private RatingBar ratingMedium;
    private EditText edtEvaluate;
    private Button btnEvaluate;
    private int idPlace;
    private  LoadingDialog loadingDialog = new LoadingDialog();


    public EvaluateDialog(int id){
        this.idPlace = id;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_evaluate_dialog,container,false);
        anhxa();
        onClicked();
        return view;
    }

    private void onClicked() {
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                loadingDialog.show(fragmentManager,"123");
                int idUser = MainActivity.sharedPreferences.getInt("idUser",0);

                if(idUser != 0 && !edtEvaluate.getText().toString().equals("")){
                    int rating = (int) ratingMedium.getRating();
                    DataService dataService = APIServices.getService();
                    Call<String>callback = dataService.postLikePlace(idUser,idPlace,edtEvaluate.getText().toString(),
                            rating,0);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful()){
                                Log.d("AAA","postLikePlace: "+response.toString());
                                Toast.makeText(view.getContext(), "Sussces", Toast.LENGTH_SHORT).show();
                                loadingDialog.dismiss();
                                getActivity().finish();
                                getActivity().startActivity(getActivity().getIntent());
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("AAA","errPostLikePlace: "+t.toString());
                            Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dismiss();
                        }
                    });
                }else {
                    Toast.makeText(view.getContext(), "Err evaluate", Toast.LENGTH_SHORT).show();
                    loadingDialog.dismiss();
                }

            }
        });
    }

    private void anhxa() {
        ratingMedium = view.findViewById(R.id.ratingMedium);
        edtEvaluate = view.findViewById(R.id.edtEvaluate);
        btnEvaluate = view.findViewById(R.id.btnEvaluate);
    }
}
