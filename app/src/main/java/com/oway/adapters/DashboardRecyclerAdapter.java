package com.oway.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oway.R;
import com.oway.callbacks.DashbordRecyclerItemclick;
import com.oway.model.DashboardGridItemsModal;

import java.util.ArrayList;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<DashboardGridItemsModal> itemList;
    private DashbordRecyclerItemclick listener;

    public DashboardRecyclerAdapter(ArrayList<DashboardGridItemsModal> itemList, Context context,DashbordRecyclerItemclick listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_screen_recyclar_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view,holder.getPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(itemList.get(position).getImageUrl()).into(holder.imageView);
        holder.textView.setText(itemList.get(position).getItemText());
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivxItems);
            textView = itemView.findViewById(R.id.tvxItemText);
        }
    }
}
