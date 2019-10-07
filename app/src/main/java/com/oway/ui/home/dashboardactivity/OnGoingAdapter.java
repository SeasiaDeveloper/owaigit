package com.oway.ui.home.dashboardactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oway.R;
import com.oway.customviews.CustomTextView;
import com.oway.model.LatestActivityModal;

import java.util.ArrayList;

public class OnGoingAdapter extends RecyclerView.Adapter<OnGoingAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LatestActivityModal> onGoingActivityModal;


    public OnGoingAdapter(ArrayList<LatestActivityModal> vehicleTypeModalArrayList, Context context) {
        this.context = context;
        this.onGoingActivityModal = vehicleTypeModalArrayList;
    }

    @NonNull
    @Override
    public OnGoingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_and_ongoing_items, parent, false);
        OnGoingAdapter.ViewHolder holder = new OnGoingAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OnGoingAdapter.ViewHolder holder, int position) {
        holder.ivxCarImage.setImageResource(onGoingActivityModal.get(position).getCarImage());
        holder.tvxTypeOfOrder.setText(onGoingActivityModal.get(position).getModeType());
        holder.tvxDestination.setText(onGoingActivityModal.get(position).getDestnation());
        holder.tvxTime.setText(onGoingActivityModal.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return onGoingActivityModal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivxCarImage;
        CustomTextView tvxTypeOfOrder;
        CustomTextView tvxDestination;
        CustomTextView tvxTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivxCarImage = itemView.findViewById(R.id.car_image);
            tvxTypeOfOrder = itemView.findViewById(R.id.tv_types);
            tvxDestination = itemView.findViewById(R.id.tv_destination_address);
            tvxTime = itemView.findViewById(R.id.tv_point_of_time);

        }
    }
}