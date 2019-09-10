package com.oway.utillis;

import android.content.Context;
import android.widget.EditText;

import com.oway.R;

public class ValidationUtils {

    private static final int CARD_MIN_LENGTH = 10;
    private final static String NULL = "null";
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
}