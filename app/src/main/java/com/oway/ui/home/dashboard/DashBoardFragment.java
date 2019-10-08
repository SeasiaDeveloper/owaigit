package com.oway.ui.home.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.oway.R;
import com.oway.adapters.DashboardRecyclerAdapter;
import com.oway.base.BaseFragment;
import com.oway.callbacks.DashbordRecyclerItemclick;
import com.oway.customviews.CustomTextView;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.DashboardGridItemsModal;
import com.oway.model.request.GetSaldoRequest;
import com.oway.model.response.GetSaldoResponse;
import com.oway.ui.trip.MotorTripActivity;
import com.oway.utillis.AppConstants;
import com.oway.utillis.GlideImageLoader;
import com.oway.utillis.ToastUtils;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardFragment extends BaseFragment implements DashBoardFragmentInterface {

    private int arr[] = {R.drawable.motor, R.drawable.car, R.drawable.box, R.drawable.burger};
    private String brr[] = {"Motor", "Mobil", "Send", "Food"};
    private ArrayList<DashboardGridItemsModal> gridItemList = new ArrayList<DashboardGridItemsModal>();
    DashboardGridItemsModal itemsModal = new DashboardGridItemsModal();
    private RecyclerView recyclerView;
    private BannerLayout bannerLayout;
    private List<String> imageUrls;
    public String balance;

    @BindView(R.id.tv_main_balance)
    CustomTextView tvxMainBalance;

    @BindView(R.id.tv_bonus_balance)
    CustomTextView txvBonusBalance;

    @Inject
    DashBoardFragmentPresenter<DashBoardFragmentInterface> dashBoardFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        dashBoardFragmentPresenter.onAttach(this);
        getSaldo();
        for (int i = 0; i <= 3; i++) {
            itemsModal = new DashboardGridItemsModal();
            itemsModal.setImageUrl(arr[i]);
            itemsModal.setItemText(brr[i]);
            gridItemList.add(itemsModal);
        }
        recyclerView = view.findViewById(R.id.rvxRecycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL));
        DashboardRecyclerAdapter adapter = new DashboardRecyclerAdapter(gridItemList, getContext(), new DashbordRecyclerItemclick() {
            @Override
            public void onItemClick(View v, int position) {
                PreferenceHandler.writeString(getActivity(), AppConstants.SELECTION_GRID, String.valueOf(position + 1));
                MotorTripActivity.startOnclick(balance,getActivity());
            }
        });
        recyclerView.setAdapter(adapter);
        bannerLayout = view.findViewById(R.id.bannerLayout);
        return view;

    }

    private void getSaldo() {
        GetSaldoRequest saldoRequest = new GetSaldoRequest();
        saldoRequest.setEkl_customer(PreferenceHandler.readString(getActivity(), AppConstants.USER_ID, ""));
        saldoRequest.setAccess_token(PreferenceHandler.readString(getActivity(), AppConstants.MBR_TOKEN, ""));
        dashBoardFragmentPresenter.getSaldo(saldoRequest);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onGetSaldoResponseSuccess(GetSaldoResponse response) {
        balance = response.getBalance().get(0).getSisa_uang();
        tvxMainBalance.setText("Rp " + response.getBalance().get(0).getSisa_uang());
        txvBonusBalance.setText("Rp bonus " + response.getBalance().get(0).getBonus_member());
        setSliderImages(response);
    }

    private void setSliderImages(GetSaldoResponse response) {
        imageUrls = new ArrayList<String>();
        for(int i=0;i<response.getImage_slider().size();i++) {
            imageUrls.add(response.getImage_slider().get(i).getUrl());
        }
        bannerLayout.setImageLoader(new GlideImageLoader());
        bannerLayout.setViewUrls(imageUrls);
    }

    @Override
    public void onGetsaldoResponseFailure(String response) {
        ToastUtils.shortToast(response);
    }
}
