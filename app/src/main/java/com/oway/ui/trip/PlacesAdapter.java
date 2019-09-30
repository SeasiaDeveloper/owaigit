package com.oway.ui.trip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oway.App;
import com.oway.R;
import com.oway.callbacks.DashbordRecyclerItemclick;
import com.oway.customviews.CustomTextView;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.utillis.AppConstants;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(AppConstants.SELECT_LATITUDE, placesList.get(position).getGeometry().getLocation().getLat());
                intent.putExtra(AppConstants.SELECT_LONGITUDE, placesList.get(position).getGeometry().getLocation().getLng());
                intent.putExtra(AppConstants.ADDRESS, placesList.get(position).getName());
                if (context instanceof Activity) {
                    ((Activity) context).setResult(Activity.RESULT_OK, intent);
                    ((Activity) context).finish();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.ViewHolder holder, int position) {

        holder.placeName.setText(placesList.get(position).getName());
        holder.placeAddressStartName.setText(placesList.get(position).getFormatted_address());
         //holder.distance.setText(placesList.get(position).getDistance().getValue());
    }


    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CustomTextView placeName;
        CustomTextView placeAddressStartName;
        CustomTextView distance;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.tv_place_name);
            placeAddressStartName = itemView.findViewById(R.id.tv_address_part_one);
            distance = itemView.findViewById(R.id.tv_distance);
        }
    }
}
