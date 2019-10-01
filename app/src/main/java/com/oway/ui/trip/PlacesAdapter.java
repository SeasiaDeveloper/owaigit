package com.oway.ui.trip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oway.R;
import com.oway.customviews.CustomTextView;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.utillis.AppConstants;

import java.util.ArrayList;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GetRecommendedPlacesResponse.ResultsBean> placesList;

    public PlacesAdapter(ArrayList<GetRecommendedPlacesResponse.ResultsBean> placesList, Context context) {
        this.context = context;
        this.placesList = placesList;
    }

    @NonNull
    @Override
    public PlacesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_places_items, parent, false);
        PlacesAdapter.ViewHolder holder = new PlacesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.ViewHolder holder, int position) {
        holder.placeName.setText(placesList.get(position).getName());
        if(placesList.get(position).getFormatted_address().isEmpty())
            holder.placeAddressStartName.setText(placesList.get(position).getName());
        else
        holder.placeAddressStartName.setText(placesList.get(position).getFormatted_address());
        holder.distance.setText(String.valueOf(placesList.get(position).getDistance().getValue()));
        holder.layMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(AppConstants.SELECT_LATITUDE, String.valueOf(placesList.get(position).getGeometry().getLocation().getLat()));
                intent.putExtra(AppConstants.SELECT_LONGITUDE, String.valueOf(placesList.get(position).getGeometry().getLocation().getLng()));
                intent.putExtra(AppConstants.ADDRESS, placesList.get(position).getName());
                if (context instanceof Activity) {
                    ((Activity) context).setResult(Activity.RESULT_OK, intent);
                    ((Activity) context).finish();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CustomTextView placeName;
        CustomTextView placeAddressStartName;
        CustomTextView distance;
        LinearLayout layMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.tv_place_name);
            placeAddressStartName = itemView.findViewById(R.id.tv_address_part_one);
            distance = itemView.findViewById(R.id.tv_distance);
            layMain=itemView.findViewById(R.id.layMain);
        }
    }
}
