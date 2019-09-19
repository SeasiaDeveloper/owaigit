package com.oway.ui.registration;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.base.BaseFragment;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.request.RegisterRequest;
import com.oway.model.response.RegisterResponse;
import com.oway.ui.home.MainActivity;
import com.oway.ui.login.LoginActivityView;
import com.oway.utillis.AppConstants;
import com.oway.utillis.ToastUtils;
import com.oway.utillis.ValidationUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegistrationViewThree extends BaseFragment implements RegisterActivityView {


    @BindView(R.id.phone_et)
    EditText phone_et;

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

    @Inject
    ValidationUtils validationUtils;


    @Inject
    RegisterActivityPresenter<LoginActivityView> registerActivityPresenter;

    private boolean isValid;

   /* @Inject
    PreferenceHandler mHandler;*/


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration_view_three, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        registerActivityPresenter.onAttach(this);
        return view;
    }

    @OnClick(R.id.buttonReg)
    public void onReg() {
        isValid = validationUtils.isRegistrationValid(phone_et, sponsorId, emailId, password, pin, alamat, kota, propinsi);
        if (isValid) {
            RegisterRequest request = new RegisterRequest();
           // request.setNama(mHandler.readString(getActivity(), AppConstants.USER_NAME,""));
            request.setEmail(emailId.getText().toString());
            request.setPassword(password.getText().toString());
            request.setPin(pin.getText().toString());
            if (sponsorId.getText().toString().isEmpty())
                request.setUplineID(getResources().getString(R.string.dummy_upline_id));
            else
                request.setUplineID(sponsorId.getText().toString());
            request.setPhone_number(phone_et.getText().toString());
            request.setAddress(alamat.getText().toString());
            request.setCity(kota.getText().toString());
            request.setProvince(propinsi.getText().toString());
            registerActivityPresenter.register(request);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onSuccess(RegisterResponse status) {
        ToastUtils.shortToast(status.getRespMessage());
        MainActivity.start((BaseActivity) getActivity());
    }

    @Override
    public void onFailure(String response) {
        ToastUtils.shortToast(response);
    }
}
