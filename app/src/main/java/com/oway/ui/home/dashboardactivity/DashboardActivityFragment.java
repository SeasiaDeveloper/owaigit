package com.oway.ui.home.dashboardactivity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.oway.R;
import com.oway.base.BaseFragment;
import com.oway.model.LatestActivityModal;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DashboardActivityFragment extends BaseFragment {

    private int cars[] = {R.drawable.motor, R.drawable.car, R.drawable.box, R.drawable.burger};
    private String types[] = {"Owai Motor", "Owai Mobil", "Owai send", "Owai food"};
    private String destination[] = {"Ride to University of Islam - lamongan", "Ride to University of Islam - lamongan", "Ride to University of Islam - lamongan", "Burger beef - Burgar restaturant"};
    private String time[] = {"8 hours ago", "9 Sep", "12 Sep", "23 Sep"};
    LatestActivityModal activityModal = new LatestActivityModal();
    ArrayList<LatestActivityModal> latestActivityModalArrayList = new ArrayList<>();
    ArrayList<LatestActivityModal> onGoingList = new ArrayList<>();

    @BindView(R.id.rv_history)
    RecyclerView rvxHistory;
    @BindView(R.id.iv_nothing_to_show)
    ImageView ivxEmptyActivities;
    @BindView(R.id.ll_no_items)
    LinearLayout llxNoItems;
    @BindView(R.id.bt_latest_activity)
    Button btxLatestActivities;
    @BindView(R.id.bt_on_going_activity)
    Button btxOnGoingActivity;

    @OnClick(R.id.bt_latest_activity)
    public void onButtonClick() {
        llxNoItems.setVisibility(View.GONE);
        rvxHistory.setVisibility(View.VISIBLE);
        btxOnGoingActivity.setBackgroundColor(Color.TRANSPARENT);
        btxLatestActivities.setBackground(getResources().getDrawable(R.drawable.button_shape_no_color));
        btxOnGoingActivity.setTextColor(getResources().getColor(R.color.col_gray));
        btxLatestActivities.setTextColor(getResources().getColor(R.color.col_orange));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvxHistory.setLayoutManager(layoutManager);
        OnGoingAdapter adapter = new OnGoingAdapter(latestActivityModalArrayList, getActivity());
        rvxHistory.setAdapter(adapter);
    }

    @OnClick(R.id.bt_on_going_activity)
    public void onClick() {
        llxNoItems.setVisibility(View.GONE);
        rvxHistory.setVisibility(View.VISIBLE);
        btxOnGoingActivity.setBackground(getResources().getDrawable(R.drawable.button_shape_no_color));
        btxOnGoingActivity.setTextColor(getResources().getColor(R.color.col_orange));
        btxLatestActivities.setTextColor(getResources().getColor(R.color.col_gray));
        btxLatestActivities.setBackgroundColor(Color.TRANSPARENT);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvxHistory.setLayoutManager(layoutManager);
        OnGoingAdapter adapter = new OnGoingAdapter(onGoingList, getActivity());
        rvxHistory.setAdapter(adapter);

    }


    public DashboardActivityFragment() {
        // Required empty public constructor
    }


    public static DashboardActivityFragment newInstance(String param1, String param2) {
        DashboardActivityFragment fragment = new DashboardActivityFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_activity, container, false);
        ButterKnife.bind(this, view);

        for (int i = 0; i <= 3; i++) {
            activityModal = new LatestActivityModal();
            activityModal.setCarImage(cars[i]);
            activityModal.setModeType(types[i]);
            activityModal.setDestnation(destination[i]);
            activityModal.setTime(time[i]);
            latestActivityModalArrayList.add(activityModal);
            onGoingList.add(0, activityModal);
        }


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

    @Override
    protected void setUp(View view) {

    }


}
