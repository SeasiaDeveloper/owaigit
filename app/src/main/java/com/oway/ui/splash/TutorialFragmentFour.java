package com.oway.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.ui.LoginSignUpChoice;
import com.oway.ui.registration.Registration;
import com.oway.utillis.AppConstants;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class TutorialFragmentFour extends Fragment {




    public TutorialFragmentFour() {
        // Required empty public constructor
    }


    public static TutorialFragmentFour newInstance(String param1, String param2) {
        TutorialFragmentFour fragment = new TutorialFragmentFour();
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
        View view=inflater.inflate(R.layout.fragment_tutorial_fragment_four, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Inject
    PreferenceHandler preferencesHelper;

    @OnClick(R.id.bt_next)
    public void onClick(){
        preferencesHelper.writeBoolean(getActivity(),AppConstants.TOTURIAL_STATUS,true);
        Registration.start((BaseActivity) getActivity());
        ((com.oway.ui.splash.Tutorial) Objects.requireNonNull(getActivity())).finish();
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
