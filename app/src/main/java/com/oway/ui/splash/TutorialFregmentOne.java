package com.oway.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.oway.R;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.ui.LoginSignUpChoice;
import com.oway.utillis.AppConstants;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class TutorialFregmentOne extends Fragment {

    @Inject
    PreferenceHandler preferenceHandler;


    public TutorialFregmentOne() {
        // Required empty public constructor
    }


    public static TutorialFregmentOne newInstance(String param1, String param2) {
        TutorialFregmentOne fragment = new TutorialFregmentOne();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_fregment_one, container, false);
        ButterKnife.bind(this,view);

        return view;

    }

    @OnClick(R.id.bt_skip_one)
    public void onClick() {
        preferenceHandler.writeBoolean(getActivity(), AppConstants.TOTURIAL_STATUS, true);
        Intent intent;
        intent = new Intent(getActivity(), LoginSignUpChoice.class);
        ((Tutorial) Objects.requireNonNull(getActivity())).startActivity(intent);
        ((Tutorial) Objects.requireNonNull(getActivity())).finish();
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
