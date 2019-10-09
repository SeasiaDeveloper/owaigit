package com.oway.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oway.R;
import com.oway.callbacks.DashbordRecyclerItemclick;
import com.oway.callbacks.PopularLocationsCallBack;
import com.oway.model.PopularLocationsModal;

import java.util.ArrayList;


public class MapPopularLocationsRecyclerAdapter extends RecyclerView.Adapter<MapPopularLocationsRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PopularLocationsModal> itemList;
    private PopularLocationsCallBack listener;

    public MapPopularLocationsRecyclerAdapter(ArrayList<PopularLocationsModal> itemList, Context context, PopularLocationsCallBack listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_popular,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(itemList.get(position).getAddress());
        holder.layPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, holder.textView.getText().toString(),itemList.get(position).getLatitude().toString(),itemList.get(position).getLongitude().toString());
            }
        });

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        LinearLayout layPlaces;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvxItemLocation);
            layPlaces=itemView.findViewById(R.id.layPlaces);
        }
    }
}

