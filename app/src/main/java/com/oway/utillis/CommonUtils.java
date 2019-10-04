/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.oway.utillis;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.oway.App;
import com.oway.R;
import com.oway.callbacks.CancelButtonClick;
import com.oway.callbacks.CancelReasonDialog;
import com.oway.callbacks.DriverProfileDialog;
import com.oway.callbacks.RegisterButtonclick;
import com.oway.customviews.CustomButton;
import com.oway.customviews.CustomEditText;
import com.oway.customviews.CustomTextView;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.response.GetEstimateBikeResponse;
import com.oway.model.response.GetNearestDriverResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;


/*import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;*/

public final class CommonUtils {

    private static final String TAG = "CommonUtils";
    private static String token = null;
    private static Map map;
    static int image;

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }


    public static String getFirebaseId() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        // Get new Instance ID token
                        token = task.getResult().getToken();
                        // Log and toast
                    }
                });
        return token;
    }

    public static void showLoginDialog(Context context, String string, RegisterButtonclick mClick) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_pop_login);
        TextView text = (TextView) dialog.findViewById(R.id.tvHeader);
        text.setText(string);
        Button btnRegisterNow = (Button) dialog.findViewById(R.id.btnRegisterNow);
       /* if (!string.contains(context.getResources().getString(R.string.phone_string)))
            btnRegisterNow.setVisibility(View.GONE);*/
        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mClick.onRegisterButtonClick();
            }
        });
        dialog.show();
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        //lWindowParams.copyFrom(context.getResources().getDialog().getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.FILL_PARENT;
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lWindowParams);
    }

    public static void showCancelDialog(Context context, CancelButtonClick cancelClick) {
        Dialog dialog = new Dialog(context, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.you_got_driver_dialog_box);
        ImageButton ibxcallDriver = dialog.findViewById(R.id.ib_call);
        ibxcallDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.callDriver();
            }
        });
        Button btnxOkOnDriverInfo = dialog.findViewById(R.id.btn_ok_driver_info);
        btnxOkOnDriverInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                cancelClick.onCancelClick();
            }
        });
        dialog.show();
    }

    public static void showCancelRideDialog(Context context, DriverProfileDialog profileDialog, double cash, double balance) {
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.map_next_button_dialog_box);
        Button btnxOrder = dialog.findViewById(R.id.btn_order);
        Button btnxCencelOrder = dialog.findViewById(R.id.btn_cencel_order);
        ImageView ivxCash = dialog.findViewById(R.id.iv_cash_dialog);
        ImageView ivxSaldo = dialog.findViewById(R.id.iv_saldo_dialog);
        CustomTextView tvCash = dialog.findViewById(R.id.tvCash);
        CustomTextView tvSaldo = dialog.findViewById(R.id.tvSaldo);
        RadioButton rbxCash = dialog.findViewById(R.id.rb_cash);

        tvCash.setText(String.valueOf(cash));
        tvSaldo.setText(String.valueOf(balance));
        RadioGroup radioGroup = dialog.findViewById(R.id.rdoGropu);
        rbxCash.setChecked(true);

        btnxCencelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                profileDialog.onCancelOrderClick();

            }
        });
        btnxOrder.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                RadioButton radioButton = (RadioButton) dialog.findViewById(selectedId);

                String amount, selection;
                if (radioButton.getText().toString().equals("CASH")) {
                    amount = tvCash.getText().toString();
                    selection = "0";
                } else {
                    amount = tvSaldo.getText().toString();
                    selection = "1";
                }
                dialog.dismiss();
                profileDialog.onOrderClick(amount, selection);
            }
        });
        dialog.show();
    }

    public static void showRideCancelReasonDialog(Context context, CancelReasonDialog cancelReasonDialog) {
        Dialog dialog = new Dialog(context, R.style.simpleDialogAlert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.cancel_order_dialog_box);
        CustomButton cancelButtonReasonDialog = dialog.findViewById(R.id.cb_cancel_reason);
        CustomButton okReason = dialog.findViewById(R.id.cb_ok_reason);
        CustomEditText notesCancelReason = dialog.findViewById(R.id.et_cancel_reason_notes);
        okReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup = dialog.findViewById(R.id.rdoReasonGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) dialog.findViewById(selectedId);
                radioButton.getText();

                String cancelReason, selection;
                if (radioButton.getText().toString().equals(AppConstants.CANCEL_REASON_ONE)) {
                    cancelReason = radioButton.getText().toString();
                    selection = "0";
                } else if (radioButton.getText().toString().equals(AppConstants.CANCEL_REASON_TWO)) {
                    cancelReason = radioButton.getText().toString();
                    selection = "1";
                } else if (radioButton.getText().toString().equals(AppConstants.CANCEL_REASON_THREE)) {
                    cancelReason = radioButton.getText().toString();
                    selection = "2";
                } else {
                    cancelReason = notesCancelReason.getText().toString();
                    selection = "3";
                }
                dialog.dismiss();
                cancelReasonDialog.onOkReasonDialogClick(cancelReason, selection);
            }
        });
        dialog.show();

    }

    public static void setDriversOnMap(GetNearestDriverResponse drives, Map map) {
        try {
            checkSelection(Integer.parseInt(PreferenceHandler.readString(App.getInstance(), AppConstants.SELECTION_GRID, "")));
            for (int i = 0; i < drives.getData().size(); i++) {
                Image imageRes = new Image();
                imageRes.setImageResource(image);
                LatLng lt = new LatLng(Double.parseDouble(drives.getData().get(i).getLatitude()), Double.parseDouble(drives.getData().get(i).getLongitude()));
                MapMarker customMarker = new MapMarker(new GeoCoordinate(lt.latitude, lt.longitude), imageRes);
                map.addMapObject(customMarker);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkSelection(int i) {
        switch (i) {
            case 1:
                image = R.drawable.bike;
                break;
            case 2:
                image = R.drawable.carcurrent;
                break;
        }
    }

    public static String getCurrentDateTime() {
        DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMAT_DD_MM_YY_HH_MM_SS);
        String date = df.format(Calendar.getInstance().getTime());
        Logger.e("ss", date);
        return date;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static void callDriver() {
        try {

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:9872465742"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            App.getInstance().startActivity(intent);
        } catch (Exception e) {

        }
    }

    public static void showCancelRide(CancelReasonDialog mClick,Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle("Cancel Ride");
        alertDialogBuilder
                .setMessage("Are you sure you want to cancel Ride?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mClick.onCancelYesClick();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}
