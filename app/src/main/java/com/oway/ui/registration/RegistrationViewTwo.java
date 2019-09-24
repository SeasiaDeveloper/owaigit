package com.oway.ui.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.oway.R;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.utillis.AppConstants;

import java.io.File;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationViewTwo extends Fragment {
    @BindView(R.id.camera_text)
    TextView camera_text;
    private View mView;

    @BindView(R.id.profile_pic)
    ImageView profile_pic;

    @Inject
    PreferenceHandler mHandler;

    public RegistrationViewTwo() {
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
        View view = inflater.inflate(R.layout.fragment_registration_view_two, container, false);
        mView = view;
        ButterKnife.bind(this, view);
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

    @OnClick(R.id.btn_reg_profile)
    public void onNextClick() {
        ((RegisterPayment) Objects.requireNonNull(getActivity())).next_fragment(mView);
    }

    @OnClick(R.id.camera_text)
    public void onImageClick() {
        ImagePicker.create(this).single().theme(R.style.ImagPickerTheme).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            List<Image> images = ImagePicker.getImages(data);
            Glide.with(getActivity()).load(new File(images.get(0).getPath())).into(profile_pic);
            PreferenceHandler.writeString(getActivity(), AppConstants.IMAGE_PATH, images.get(0).getPath());
        }
    }
    @OnClick(R.id.backBtn)
    public void onBackClick() {
        getActivity().finish();
    }
}
