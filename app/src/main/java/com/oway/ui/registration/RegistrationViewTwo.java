package com.oway.ui.registration;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oway.R;

import java.util.Objects;


public class RegistrationViewTwo extends Fragment {

    public RegistrationViewTwo() {
        // Required empty public constructor
    }


    public static RegistrationViewTwo newInstance(String param1, String param2) {
        RegistrationViewTwo fragment = new RegistrationViewTwo();
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
        View view=inflater.inflate(R.layout.fragment_registration_view_two, container, false);

        Button btnxNext=(Button)view.findViewById(R.id.btn_reg_profile);
        btnxNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RegisterPayment) Objects.requireNonNull(getActivity())).next_fragment(view);
            }
        });

        // Inflate the layout for this fragment
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

}
