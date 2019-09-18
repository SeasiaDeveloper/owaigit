package com.oway.ui.registration;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.ui.LoginSignUpChoice;
import com.oway.ui.home.MainActivity;
import com.oway.ui.home.dashboard.DashBoardFragment;
import com.oway.ui.splash.Tutorial;
import com.oway.utillis.ValidationUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegistrationViewThree extends Fragment {

    ValidationUtils validationUtils=new ValidationUtils(getContext());

    public RegistrationViewThree() {
        // Required empty public constructor
    }


    public static RegistrationViewThree newInstance(String param1, String param2) {
        RegistrationViewThree fragment = new RegistrationViewThree();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @BindView(R.id.sponsor_et)
    EditText sponsorId;
    @BindView(R.id.et_mail)
    EditText emailId;
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.et_pincode)
    EditText pin;
    @BindView(R.id.et_alamat)
    EditText alamat;
    @BindView(R.id.et_kota)
    EditText kota;
    @BindView(R.id.et_propinsi)
    EditText propinsi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registration_view_three, container, false);
        ButterKnife.bind(this,view);


        return view;
    }

    @OnClick(R.id.buttonReg)
    public void onReg(){
        Intent main=new Intent(getActivity(),MainActivity.class);
        startActivity(main);
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
