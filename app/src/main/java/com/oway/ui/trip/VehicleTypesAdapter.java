package com.oway.ui.trip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.oway.R;
import com.oway.customviews.CustomTextView;
import com.oway.model.VehicleTypeModal;

import java.util.ArrayList;


public class VehicleTypesAdapter extends RecyclerView.Adapter<VehicleTypesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<VehicleTypeModal> vehicleTypeModalArrayList;

    public VehicleTypesAdapter(ArrayList<VehicleTypeModal> vehicleTypeModalArrayList, Context context) {
        this.context = context;
        this.vehicleTypeModalArrayList = vehicleTypeModalArrayList;
    }

    @NonNull
    @Override
    public VehicleTypesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_type_items, parent, false);
        VehicleTypesAdapter.ViewHolder holder = new VehicleTypesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleTypesAdapter.ViewHolder holder, int position) {
        holder.carImage.setImageResource(vehicleTypeModalArrayList.get(position).getCarImage());
        holder.seats.setText(vehicleTypeModalArrayList.get(position).getNoOfSeats());
        holder.people.setText(vehicleTypeModalArrayList.get(position).getNoOfPeople());
        holder.amount.setText(vehicleTypeModalArrayList.get(position).getAmountToPay());
        if (vehicleTypeModalArrayList.get(position).isSelect())
            holder.layCar.setBackgroundResource(R.drawable.vehicle_type_item_shape_yellow);
        else
            holder.layCar.setBackgroundResource(R.drawable.vehicle_type_item_shape);
        holder.layCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < vehicleTypeModalArrayList.size(); i++)
                    vehicleTypeModalArrayList.get(i).setSelect(false);
                vehicleTypeModalArrayList.get(position).setSelect(true);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicleTypeModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView carImage;
        CustomTextView seats;
        CustomTextView people;
        CustomTextView amount;
        LinearLayout layCar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.civ_car_image);
            seats = itemView.findViewById(R.id.tv_no_of_seats);
            people = itemView.findViewById(R.id.tv_no_of_people);
            amount = itemView.findViewById(R.id.tv_amount_to_be_paid);
            layCar = itemView.findViewById(R.id.layCar);

        }
    }
}