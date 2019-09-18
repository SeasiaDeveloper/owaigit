package com.oway.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oway.R;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.datasource.pref.PreferencesHelper;
import com.oway.ui.LoginSignUpChoice;
import com.oway.ui.login.LoginActivity;
import com.oway.utillis.AppConstants;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link TutorialFragmentThree#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorialFragmentThree extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button skipThree;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

 //   private OnFragmentInteractionListener mListener;

    public TutorialFragmentThree() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TutorialFragmentThree.
     */
    // TODO: Rename and change types and number of parameters
    public static TutorialFragmentThree newInstance(String param1, String param2) {
        TutorialFragmentThree fragment = new TutorialFragmentThree();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tutorial_fragment_three, container, false);

        ButterKnife.bind(this,view);
        return view;
    }
    @Inject
    PreferenceHandler preferencesHelper;

    @OnClick(R.id.bt_skip_three)
    public void onClick(){
        preferencesHelper.writeBoolean(getActivity(),AppConstants.TOTURIAL_STATUS,true);
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
