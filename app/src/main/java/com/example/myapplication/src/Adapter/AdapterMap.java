package com.example.myapplication.src.Adapter;

import android.location.Address;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.src.Activity.MapActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class AdapterMap extends RecyclerView.Adapter<AdapterMap.Viewhdler> {
    private List<Address> addressArrayList;
    private MapActivity context;
    private int layout;

    public AdapterMap(List<Address> addressArrayList, MapActivity context, int layout) {
        this.addressArrayList = addressArrayList;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public Viewhdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,layout,null);
        return new Viewhdler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhdler holder, final int position) {
        holder.txtNameMap.setText(addressArrayList.get(position).getFeatureName());
        holder.txtIntroduceMap.setText(addressArrayList.get(position).getAddressLine(position));
        holder.carrtMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.moveCamera(new LatLng(addressArrayList.get(position).getLatitude(),addressArrayList.get(position)
                .getLongitude()),17,addressArrayList.get(position).getAddressLine(position),true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressArrayList.size();
    }

    public class Viewhdler extends RecyclerView.ViewHolder{
        private TextView txtIntroduceMap,txtNameMap;
        private CardView carrtMap;
        public Viewhdler(@NonNull View itemView) {
            super(itemView);
            txtIntroduceMap = itemView.findViewById(R.id.txtIntroduceMap);
            txtNameMap = itemView.findViewById(R.id.txtNameMap);
            carrtMap = itemView.findViewById(R.id.carrtMap);
        }
    }
}
