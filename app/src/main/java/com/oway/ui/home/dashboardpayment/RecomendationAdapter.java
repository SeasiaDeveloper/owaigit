package com.oway.ui.home.dashboardpayment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oway.R;
import com.oway.customviews.CustomTextView;
import com.oway.model.PaymentFragmentModal;

import java.util.ArrayList;



public class RecomendationAdapter extends RecyclerView.Adapter<RecomendationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PaymentFragmentModal> paymentFragmentModalArrayList;


    public RecomendationAdapter(ArrayList<PaymentFragmentModal> vehicleTypeModalArrayList, Context context) {
        this.context = context;
        this.paymentFragmentModalArrayList = vehicleTypeModalArrayList;
    }

    @NonNull
    @Override
    public RecomendationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_fragment_recycler, parent, false);
        RecomendationAdapter.ViewHolder holder = new RecomendationAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendationAdapter.ViewHolder holder, int position) {
        holder.llxBackImage.setBackgroundResource(paymentFragmentModalArrayList.get(position).getImageBack());
        holder.tvxTypeOfOffer.setText(paymentFragmentModalArrayList.get(position).getNameApp());
        holder.tvxDiscription.setText(paymentFragmentModalArrayList.get(position).getDiscription());

    }

    @Override
    public int getItemCount() {
        return paymentFragmentModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llxBackImage;
        CustomTextView tvxTypeOfOffer;
        CustomTextView tvxDiscription;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llxBackImage=itemView.findViewById(R.id.ll_back_payment);
            tvxTypeOfOffer = itemView.findViewById(R.id.tv_rec_app_name);
            tvxDiscription = itemView.findViewById(R.id.tv_rec_discription);

        }
    }
}