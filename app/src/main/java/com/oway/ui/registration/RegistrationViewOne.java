package com.oway.ui.registration;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.oway.R;

import java.util.Objects;


public class RegistrationViewOne extends Fragment {


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
        Button btnxNext = (Button) view.findViewById(R.id.btn_next);
        btnxNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RegisterPayment) Objects.requireNonNull(getActivity())).next_fragment(view);
            }
        });
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
