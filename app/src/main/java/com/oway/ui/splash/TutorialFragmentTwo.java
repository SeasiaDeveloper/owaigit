package com.oway.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.ui.LoginSignUpChoice;
import com.oway.ui.registration.Registration;
import com.oway.utillis.AppConstants;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p>
 * to handle interaction events.
 * Use the {@link TutorialFragmentTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorialFragmentTwo extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // private OnFragmentInteractionListener mListener;

    public TutorialFragmentTwo() {
        // Required empty public constructor
    }


    public static TutorialFragmentTwo newInstance(String param1, String param2) {
        TutorialFragmentTwo fragment = new TutorialFragmentTwo();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @BindView(R.id.bt_skip_two)
    Button btnSkipTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_fragment_two, container, false);
        ButterKnife.bind(Objects.requireNonNull(this),view);
        return view;
    }

    @Inject
    PreferenceHandler preferencesHelper;

    @OnClick(R.id.bt_skip_two)
    public void onClick() {
        preferencesHelper.writeBoolean(getActivity(),AppConstants.TOTURIAL_STATUS, true);
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
