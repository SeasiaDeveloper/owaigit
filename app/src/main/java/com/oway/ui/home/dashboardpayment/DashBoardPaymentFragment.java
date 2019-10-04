package com.oway.ui.home.dashboardpayment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oway.R;
import com.oway.model.PaymentFragmentModal;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DashBoardPaymentFragment extends Fragment {

    @BindView(R.id.rv_payment_offers)
    RecyclerView rvxPayments;

    private int backImage[] = {R.drawable.offer_one, R.drawable.offer_two};
    private String typesOffer[] = {"OTU Chat", "GOWIST"};
    private String discription[] = {"Gunakan saldo OTUmu, nikmati kemudahan transaksi dan dapatkan bonusnya", "Lebih aman, pergi kemana aja, kapan aja dan dimana aja"};
    PaymentFragmentModal paymentFragmentModal = new PaymentFragmentModal();
    ArrayList<PaymentFragmentModal> offerList = new ArrayList<>();

    public DashBoardPaymentFragment() {
        // Required empty public constructor
    }


    public static DashBoardPaymentFragment newInstance(String param1, String param2) {
        DashBoardPaymentFragment fragment = new DashBoardPaymentFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board_payment, container, false);
        ButterKnife.bind(this, view);

        for (int i = 0; i <= 1; i++) {
            paymentFragmentModal = new PaymentFragmentModal();
            paymentFragmentModal.setImageBack(backImage[i]);
            paymentFragmentModal.setNameApp(typesOffer[i]);
            paymentFragmentModal.setDiscription(discription[i]);
            offerList.add(paymentFragmentModal);
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvxPayments.setLayoutManager(layoutManager);
        RecomendationAdapter adapter=new RecomendationAdapter(offerList,getActivity());
        rvxPayments.setAdapter(adapter);

        return view;
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
