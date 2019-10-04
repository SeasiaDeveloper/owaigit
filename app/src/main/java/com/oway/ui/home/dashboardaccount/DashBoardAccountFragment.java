package com.oway.ui.home.dashboardaccount;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oway.R;
import com.oway.utillis.CommonUtils;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class DashBoardAccountFragment extends Fragment {
    @OnClick(R.id.ll_edit_profile)
    public void onEditprofileClick() {
        EditProfileActivity.onStartActivity(Objects.requireNonNull(getActivity()));
    }

    @OnClick(R.id.ll_help_center)
    public void onHelpClick() {
        AccountHelpCenter.onStartHelpCenter(Objects.requireNonNull(getActivity()));
    }

    @OnClick(R.id.ll_emergency_call)
    public void onCallClick() {
        AccountFragmentEmergencyCall.onStartCall(Objects.requireNonNull(getActivity()));
    }

    @OnClick(R.id.ll_settings)
    public void onSettingClick() {
        AccountFragmentSettingActivity.onStartSettings(Objects.requireNonNull(getActivity()));
    }

    @OnClick(R.id.ll_report_services)
    public void onReportClick() {
        DashboardAccountReportServices.onStartReportServices(Objects.requireNonNull(getActivity()));
    }
    @OnClick(R.id.ll_logout)
    public void onLogoutClick() {
        CommonUtils.showLogoutDialog(getActivity());
    }
    public DashBoardAccountFragment() {
        // Required empty public constructor
    }


    public static DashBoardAccountFragment newInstance(String param1, String param2) {
        DashBoardAccountFragment fragment = new DashBoardAccountFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board_account, container, false);
        ButterKnife.bind(this, view);
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
