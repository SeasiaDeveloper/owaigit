package com.oway.utillis;

import android.content.Context;
import android.widget.EditText;

import com.oway.R;

public class ValidationUtils {

    private Context context;

    public ValidationUtils(Context context) {
        this.context = context;
    }

    public boolean isLoginDataValid(EditText etxName,
                                    EditText etxPassword) {
        if (etxName.getText().toString().isEmpty()) {
            etxName.setError(context.getString(R.string.validate_name));
            return false;
        } else if (etxPassword.getText().toString().isEmpty()) {
            etxPassword.setError(context.getString(R.string.validate_password));
            return false;
        }
        return true;
    }

    public boolean isRegistrationValid(EditText etxPhoneNumber,EditText etxsponsorId, EditText etxEmail,
                                       EditText etxPassword, EditText etxPin, EditText etxAlamat, EditText etxKota, EditText etxPropinsi
    ) {
        if(etxPhoneNumber.getText().toString().isEmpty())
        {
            etxPhoneNumber.setError(context.getString(R.string.validate_phonenumber));
            return false;
        }
       /* else if (etxsponsorId.getText().toString().isEmpty()) {
            etxsponsorId.setError(context.getString(R.string.validate_sponsorId));
            return false;
        }*/ else if (etxEmail.getText().toString().isEmpty()) {
            etxEmail.setError(context.getString(R.string.validate_email));
            return false;
        } else if (etxPassword.getText().toString().isEmpty()) {
            etxPassword.setError(context.getString(R.string.validate_password));
            return false;
        } else if (etxPin.getText().toString().isEmpty()) {
            etxPin.setError(context.getString(R.string.validate_pin));
            return false;
        } else if (etxAlamat.getText().toString().isEmpty()) {
            etxAlamat.setError(context.getString(R.string.validate_alamat));
            return false;
        } else if (etxKota.getText().toString().isEmpty()) {
            etxKota.setError(context.getString(R.string.validate_kota));
            return false;
        } else if (etxPropinsi.getText().toString().isEmpty()) {
            etxPropinsi.setError(context.getString(R.string.validate_propinsi));
            return false;
        }
        return true;
    }


}