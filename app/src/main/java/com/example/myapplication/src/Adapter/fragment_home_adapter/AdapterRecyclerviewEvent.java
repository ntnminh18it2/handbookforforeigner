package com.example.myapplication.src.Adapter.fragment_home_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.event_reponse.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecyclerviewEvent extends RecyclerView.Adapter<AdapterRecyclerviewEvent.ViewHodler>{
    private Context context;
    private int layout;
    private ArrayList<Event>arrayList;

    public AdapterRecyclerviewEvent(Context context, int layout, ArrayList<Event> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,layout,null);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, final int position) {
        Picasso.with(context).load(arrayList.get(position).getImage()).into(holder.mImgItemEventHomepage);
        String arr[] = arrayList.get(position).getUpdated_at().split("T");
        String arrDate[] =arr[0].split("-");
        holder.mTxtDay.setText(arrDate[0]);
        holder.mTxtMound.setText("TH "+arrDate[1]);
        holder.mLinerLayoutEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Chua Hoan Thanh", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
       return arrayList.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder{
        private ImageView mImgItemEventHomepage;
        private TextView mTxtMound;
        private TextView mTxtDay;
        private LinearLayout mLinerLayoutEvent;
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            mImgItemEventHomepage = itemView.findViewById(R.id.imgItemEventHomepage);
            mTxtDay = itemView.findViewById(R.id.txtDay);
            mTxtMound = itemView.findViewById(R.id.txtMounth);
            mLinerLayoutEvent = itemView.findViewById(R.id.linerLayoutEvent);
        }
    }
}
