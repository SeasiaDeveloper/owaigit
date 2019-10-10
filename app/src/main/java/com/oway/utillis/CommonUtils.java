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


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapRoute;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.oway.App;
import com.oway.R;
import com.oway.callbacks.CancelButtonClick;
import com.oway.callbacks.CancelReasonDialog;
import com.oway.callbacks.DriverProfileDialog;
import com.oway.callbacks.RegisterButtonclick;
import com.oway.callbacks.TermsAndConditionCallBack;
import com.oway.customviews.CustomButton;
import com.oway.customviews.CustomEditText;
import com.oway.customviews.CustomTextView;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.response.GetNearestDriverResponse;
import com.oway.model.response.PushNotificationResponse;
import com.oway.model.response.PushNotificationResponseStart;
import com.oway.otto.OnApplyPushNotificationEventArrived;
import com.oway.otto.OnApplyPushNotificationEventArrivingNow;
import com.oway.otto.OnApplyPushNotificationEventTripStart;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;


/*import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;*/

public final class CommonUtils {

    private static final String TAG = "CommonUtils";
    private static MapRoute mapRoute = null;
    private static String token = null;
    static int image;
    static Map mMap;
    private static MapMarker mapMarker = null;
    private static MapMarker mapMarker_source = null;
    private static List<MapObject> marker=new ArrayList<>();

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (progressDialog.getWindow() != null) {
            progressDialog.show();
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

    public static void showCancelDialog(Context context, CancelButtonClick cancelClick, OnApplyPushNotificationEventArrivingNow event) {
        Dialog dialog = new Dialog(context, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.you_got_driver_dialog_box);
        ImageButton ibxcallDriver = dialog.findViewById(R.id.ib_call);

        ImageView imgDriver = (ImageView) dialog.findViewById(R.id.imgDriver);
        TextView tvDriverName = (TextView) dialog.findViewById(R.id.tvDriverName);
        TextView tvModel = (TextView) dialog.findViewById(R.id.tvModel);
        TextView tvSubModel = (TextView) dialog.findViewById(R.id.tvSubModel);
        RatingBar rtBa = (RatingBar) dialog.findViewById(R.id.rtBar);
        TextView tvEstimationTime = (TextView) dialog.findViewById(R.id.tvEstimationTime);

        tvDriverName.setText(event.getDriver_name());
        tvModel.setText(event.getType_vehicle());
        tvSubModel.setText(event.getVehicle());
        rtBa.setRating(Float.parseFloat(String.valueOf(event.getRating())));
        tvEstimationTime.setText(event.getReach_estimate());

        Glide.with(context).load(event.getDriver_picture()).into(imgDriver);

        ibxcallDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.callDriver(event.getDriver_phone(), context);
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
        if (!dialog.isShowing())
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

        CustomButton cb_cancel_reason = dialog.findViewById(R.id.cb_cancel_reason);
        cb_cancel_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

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

    public static void callDriver(String driver_phone, Context context) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + driver_phone));
            context.startActivity(callIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showCancelRide(CancelReasonDialog mClick, Context context) {
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



    public static PushNotificationResponseStart getNotificationArrivedNowResponse(java.util.Map<String, String> data) {
        PushNotificationResponseStart response = new PushNotificationResponseStart();
        response.setType(data.get("type"));
        response.setFeature(Integer.parseInt(data.get("feature")));
        response.setId_transaksi(Integer.parseInt(data.get("id_transaksi")));
        response.setStatus(data.get("status"));
        response.setEkl_pelanggan(data.get("ekl_pelanggan"));
        response.setLatitude_start(data.get("latitude_start"));
        response.setLongitude_start(data.get("longitude_start"));
        response.setLatitude_end(data.get("latitude_end"));
        response.setDistance(Integer.parseInt(data.get("distance")));
        //response.setDistance(12);
        response.setReach_estimate(data.get("reach_estimate"));
        //response.setPrice(12);
        response.setPrice(Integer.parseInt(data.get("price")));
        response.setOrder_time("");
        response.setPickup_address(data.get("pickup_address"));
        response.setDestination_address(data.get("destination_address"));
        response.setUsing_balance(Integer.parseInt(data.get("using_balance")));
        response.setMessage(data.get("message"));
        response.setMessage_id(String.valueOf(data.get("message_id")));
        response.setDriver_picture(data.get("driver_picture"));
        response.setDriver_name(data.get("driver_name"));
        response.setType_vehicle(data.get("type_vehicle"));
        response.setVehicle(data.get("vehicle"));
        return response;
    }

    public static void showLogoutDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.logout_dialog);
        dialog.show();
    }

    public static void showPopUpWindow(Context context, Activity activity, RelativeLayout layout, TermsAndConditionCallBack conditionCallBack) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        View viewGroup = activity.getLayoutInflater().inflate(R.layout.terms_and_condition, null, false);

        PopupWindow popupWindow = new PopupWindow(viewGroup, width, height);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
        Button btxDismiss = viewGroup.findViewById(R.id.bt_dismiss_popup);
        btxDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                conditionCallBack.onCancelConditionClick();
            }
        });
        Button btxUnderstand = viewGroup.findViewById(R.id.bt_understand);
        btxUnderstand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                conditionCallBack.onYesClick();
            }
        });
    }

    public static void setDrivingArrivingData(RelativeLayout layoutDriverRidingToYou, OnApplyPushNotificationEventArrivingNow mEvent) {
        TextView tvEstimationTime = (TextView) layoutDriverRidingToYou.findViewById(R.id.tvEstimateTimeArriving);
        tvEstimationTime.setText(mEvent.getReach_estimate());
    }

    public static void setBottomSheetData(Context context, RelativeLayout bottomSheet, OnApplyPushNotificationEventArrivingNow mEvent) {
        ImageView imageDriver = (ImageView) bottomSheet.findViewById(R.id.iv_driver_image);
        TextView tv_driver_name = (TextView) bottomSheet.findViewById(R.id.tv_driver_name);
        TextView tv_driver_car_name = (TextView) bottomSheet.findViewById(R.id.tv_driver_car_name);
        TextView tv_driver_sub_car_name = (TextView) bottomSheet.findViewById(R.id.tv_driver_sub_car_name);
        ImageView ib_message = (ImageView) bottomSheet.findViewById(R.id.ib_message);
        ImageView ib_call_driver = (ImageView) bottomSheet.findViewById(R.id.ib_call_driver);

        TextView tv_text_one_slider = (TextView) bottomSheet.findViewById(R.id.tv_text_one_slider);

        Glide.with(App.getInstance()).load(mEvent.getDriver_picture()).into(imageDriver);
        tv_driver_name.setText(mEvent.getDriver_name());
        tv_driver_car_name.setText(mEvent.getType_vehicle());
        tv_driver_sub_car_name.setText(mEvent.getVehicle());
        tv_text_one_slider.setText(mEvent.getMessage());
        ib_call_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.callDriver(mEvent.getDriver_phone(), context);
            }
        });

        ImageView imageDriverSlider = (ImageView) bottomSheet.findViewById(R.id.driverImageSlider);
        TextView tvDriverNameSlider = (TextView) bottomSheet.findViewById(R.id.tvDriverNameSlider);
        TextView tvCarNameSlider = (TextView) bottomSheet.findViewById(R.id.tvCarNameSlider);
        TextView tvSubCarNameSlider = (TextView) bottomSheet.findViewById(R.id.tvSubCarNameSlider);
        RatingBar rtBa = (RatingBar) bottomSheet.findViewById(R.id.rtBar);
        rtBa.setRating(Float.parseFloat(String.valueOf(mEvent.getRating())));

        //ImageView ib_message = (ImageView) bottomSheet.findViewById(R.id.ib_message);
        ImageView ib_call_driver_bottom_sheet = (ImageView) bottomSheet.findViewById(R.id.ib_call_driver_bottom_sheet);
        TextView tvArrivedTimeSlider = (TextView) bottomSheet.findViewById(R.id.tvArrivedTimeSlider);
        Glide.with(App.getInstance()).load(mEvent.getDriver_picture()).into(imageDriverSlider);
        tvDriverNameSlider.setText(mEvent.getDriver_name());
        tvCarNameSlider.setText(mEvent.getType_vehicle());
        tvSubCarNameSlider.setText(mEvent.getVehicle());
        tvArrivedTimeSlider.setText(mEvent.getReach_estimate());
        ib_call_driver_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.callDriver(mEvent.getDriver_phone(), context);
            }
        });
    }

    public static void setTripStartData(Context context, RelativeLayout bottomSheet, OnApplyPushNotificationEventTripStart mEvent) {
        TextView tv_text_one_loc = (TextView) bottomSheet.findViewById(R.id.tv_text_one_loc);
        TextView tv_text_two_loc = (TextView) bottomSheet.findViewById(R.id.tv_text_two_loc);
        TextView tv_source = (TextView) bottomSheet.findViewById(R.id.tv_pick_loc);
        TextView tv_destination = (TextView) bottomSheet.findViewById(R.id.tv_drop_loc);
        TextView tv_distance = (TextView) bottomSheet.findViewById(R.id.tvDistance);
        TextView tv_description = (TextView) bottomSheet.findViewById(R.id.tvDescription);
        TextView tv_price = (TextView) bottomSheet.findViewById(R.id.tvPrice);
        TextView tvCash=(TextView)bottomSheet.findViewById(R.id.tvCash);

        ImageView iv_driver_image_below_loc = (ImageView) bottomSheet.findViewById(R.id.iv_driver_image_below_loc);
        TextView tv_driver_name_loc = (TextView) bottomSheet.findViewById(R.id.tv_driver_name_loc);
        TextView tv_driver_car_name_loc = (TextView) bottomSheet.findViewById(R.id.tv_driver_car_name_loc);
        TextView tvModel = (TextView) bottomSheet.findViewById(R.id.tvModel);


        Glide.with(App.getInstance()).load(mEvent.getDriver_picture()).into(iv_driver_image_below_loc);
        tv_driver_name_loc.setText(mEvent.getDriver_name());
        tv_driver_car_name_loc.setText(mEvent.getType_vehicle());
        tvModel.setText(mEvent.getVehicle());

        tv_text_one_loc.setText(mEvent.getMessage());
        tv_text_two_loc.setText(mEvent.getReach_estimate());
        tv_source.setText(mEvent.getPickup_address());
        tv_destination.setText(mEvent.getDestination_address());
        tv_distance.setText(String.valueOf(mEvent.getDistance())+" KM");
        tv_description.setText(mEvent.getReach_estimate());
        tv_price.setText("RP "+String.valueOf(mEvent.getPrice()));
        if(mEvent.getUsing_balance()==0)
            tvCash.setText(context.getResources().getString(R.string.tv_cash));
        else
            tvCash.setText(context.getResources().getString(R.string.saldo));
    }

    public static OnApplyPushNotificationEventArrivingNow cloneObject(OnApplyPushNotificationEventArrived arrived) {
        OnApplyPushNotificationEventArrivingNow arrivingNow = new OnApplyPushNotificationEventArrivingNow(arrived.getType(), arrived.getFeature(), arrived.getId_transaksi(), arrived.getStatus(), arrived.getEkl_driver(), arrived.getDriver_name(), arrived.getDriver_picture(), arrived.getNopal(), arrived.getType_vehicle(), arrived.getVehicle(), arrived.getColor(), arrived.getRating(), "Make Sure your's location", arrived.getDriver_phone(), arrived.getMessage(), arrived.getMessage_id());
        return arrivingNow;
    }

    public static PushNotificationResponse getNotificationResponse(java.util.Map<String, String> data) {
        PushNotificationResponse response = new PushNotificationResponse();
        response.setType(data.get("type"));
        response.setFeature(Integer.parseInt(data.get("feature")));
        response.setId_transaksi(Integer.parseInt(data.get("id_transaksi")));
        response.setStatus(data.get("status"));
        response.setEkl_driver(data.get("ekl_driver"));
        response.setDriver_name(data.get("driver_name"));
        response.setDriver_picture(data.get("driver_picture"));
        response.setNopol(data.get("nopol"));
        response.setType_vehicle(data.get("type_vehicle"));
        response.setVehicle(data.get("vehicle"));
        response.setColor(data.get("color"));
        return response;
    }

    public static void getDirections(Map map, double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        // 1. clear previous results
        mMap = map;
        if (map != null && mapRoute != null) {
           // map.removeMapObject(mapRoute);
           // map.removeMapObject(mapMarker_source);
            //map.removeMapObject(mapMarker);
            mapRoute = null;
            map.removeMapObjects(marker);
        }

        // 2. Initialize RouteManager
        RouteManager routeManager = new RouteManager();

        // 3. Select routing options
        RoutePlan routePlan = new RoutePlan();

        RouteOptions routeOptions = new RouteOptions();
        routeOptions.setTransportMode(RouteOptions.TransportMode.CAR);
        routeOptions.setRouteType(RouteOptions.Type.FASTEST);
        routePlan.setRouteOptions(routeOptions);
        Image image = new Image();


        // 4. Select Waypoints for your routes
        // START: Nokia, Burnaby
        routePlan.addWaypoint(new GeoCoordinate(startLatitude, startLongitude));
        try {
            image.setImageResource(R.drawable.source_mark);
            mapMarker_source = new MapMarker(new GeoCoordinate(startLatitude, startLongitude, 0.0), image);
            Objects.requireNonNull(map).addMapObject(mapMarker_source);
            marker.add(mapMarker_source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // END: Airport, YVR
        routePlan.addWaypoint(new GeoCoordinate(endLatitude, endLongitude));
        try {
            image.setImageResource(R.drawable.destination_mark);
            mapMarker = new MapMarker(new GeoCoordinate(endLatitude, endLongitude, 0.0), image);
            Objects.requireNonNull(map).addMapObject(mapMarker);
            marker.add(mapMarker);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 5. Retrieve Routing information via RouteManagerEventListener
        RouteManager.Error error = routeManager.calculateRoute(routePlan, routeManagerListener);
        if (error != RouteManager.Error.NONE) {
            Toast.makeText(getApplicationContext(),
                    "Route calculation failed with: " + error.toString(), Toast.LENGTH_SHORT)
                    .show();
        }


    }


    private static RouteManager.Listener routeManagerListener = new RouteManager.Listener() {
        public void onCalculateRouteFinished(RouteManager.Error errorCode,
                                             List<RouteResult> result) {

            if (errorCode == RouteManager.Error.NONE && result.get(0).getRoute() != null) {
                // create a map route object and place it on the map
                mapRoute = new MapRoute(result.get(0).getRoute());
                Objects.requireNonNull(mapRoute).setColor(R.color.col_gray);
                mMap.addMapObject(mapRoute);

                // Get the bounding box containing the route and zoom in (no animation)
                GeoBoundingBox gbb = result.get(0).getRoute().getBoundingBox();
                mMap.zoomTo(gbb, Map.Animation.NONE, Map.MOVE_PRESERVE_ORIENTATION);
                marker.add(mapRoute);

            }
        }

        public void onProgress(int percentage) {

        }
    };
}
