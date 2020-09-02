package com.example.myapplication.src.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.details_reponse.DataEvaluate;
import com.example.myapplication.services.APIServices;
import com.example.myapplication.services.DataService;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterRecyclerviewComment extends RecyclerView.Adapter<AdapterRecyclerviewComment.Viewhodler> {
    private ArrayList<DataEvaluate>arrayList;
    private Context context;
    private int layout;
    private int number;

    public AdapterRecyclerviewComment(ArrayList<DataEvaluate> arrayList, Context context, int layout, int number) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout = layout;
        this.number = number;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,layout,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, int position) {
        if(arrayList.get(position).getComment().equals("null")){

        }else{
            holder.txtTitleComment.setText("");
            getNameUserComment(holder.txtTitleComment,arrayList.get(position).getIdUser());
            holder.ratingBar.setRating(arrayList.get(position).getRating());
            holder.txtContentComment.setText(arrayList.get(position).getComment());
            String[] date = arrayList.get(position).getUpdatedAt().split("T");
            holder.txtDateComment.setText(date[0]);
        }

    }

    private void getNameUserComment(final TextView txtTitleComment, Integer idUser) {
        DataService dataService = APIServices.getService();
        Call<String>call = dataService.getNameUser(idUser);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    txtTitleComment.setText(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return number;
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private RoundedImageView roundImageviewComment;
        private RatingBar ratingBar;
        private TextView txtTitleComment,txtDateComment,txtContentComment;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            roundImageviewComment = itemView.findViewById(R.id.roundImageviewComment);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            txtTitleComment = itemView.findViewById(R.id.txtTitleComment);
            txtDateComment = itemView.findViewById(R.id.txtDateComment);
            txtContentComment = itemView.findViewById(R.id.txtContentComment);
        }
    }
}
