package com.oway.ui.registration;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.oway.R;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.utillis.AppConstants;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegistrationViewOne extends Fragment {

    @BindView(R.id.etxName)
    EditText etxName;
    private View mView;

    @Inject
    PreferenceHandler mHandler;

    public RegistrationViewOne() {
    }


    public static RegistrationViewOne newInstance(String param1, String param2) {
        RegistrationViewOne fragment = new RegistrationViewOne();
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
        View view = inflater.inflate(R.layout.fragment_registration_view_one, container, false);
        mView = view;
        ButterKnife.bind(Objects.requireNonNull(this), view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.btn_next)
    public void onNextClick() {
         mHandler.writeString(getActivity(), AppConstants.USER_NAME,etxName.getText().toString());
        ((RegisterPayment) Objects.requireNonNull(getActivity())).next_fragment(mView);
    }
    @OnClick(R.id.backBtn)
    public void onBackClick() {
        getActivity().finish();
    }
}
