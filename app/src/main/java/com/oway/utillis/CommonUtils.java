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
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.SupportMapFragment;
import com.oway.App;
import com.oway.R;
import com.oway.callbacks.CancelButtonClick;
import com.oway.callbacks.CancelReasonDialog;
import com.oway.callbacks.DriverProfileDialog;
import com.oway.callbacks.RegisterButtonclick;
import com.oway.customviews.CustomButton;
import com.oway.customviews.CustomEditText;
import com.oway.customviews.CustomTextView;
import com.oway.model.response.GetEstimateBikeResponse;
import com.oway.model.response.GetNearestDriverResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static androidx.core.content.ContextCompat.checkSelfPermission;
import static com.facebook.accountkit.internal.AccountKitController.getApplicationContext;


/*import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;*/

public final class CommonUtils {

    private static final String TAG = "CommonUtils";
    private static String token = null;
    private static Map map;

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


    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getIntegerWithDecimalValue(String value) {
        String finalVal = value;
        try {
            finalVal = String.format("%.2f", Float.parseFloat(value));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return finalVal;
    }


    public static void openDialPad(String phoneNo, Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNo));
        context.startActivity(intent);
    }

    public static int convertDPtoPX(int dpValue, Context mContext) {
        Resources r = mContext.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                r.getDisplayMetrics()
        );
    }

    public static String formatToCommaFormattedAmt(String amount) {
        return String.format(Locale.CANADA, "%,.02f", Double.parseDouble(amount));
    }


 /*   public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }*/

    public static String compressImage(String imageUri, Context context) {

        String filePath = getRealPathFromURI(imageUri, context);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 600.0f;
        float maxWidth = 500.0f;
        // float maxHeight = 816.0f;
        //  float maxWidth = 612.0f;

        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;
    }

    public static String getFilename() {

        File file = new File(Environment.getExternalStorageDirectory() + "/" + AppConstants.DIRECTORY_APP_NAME + "/" + AppConstants.COMPRESSES_IMAGES);
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }


    private static String getRealPathFromURI(String contentURI, Context context) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    public static void LockScrollViewOnViewTouch(final ScrollView scrollView, View view) {
        view.setOnTouchListener(new android.view.View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View p_v, MotionEvent p_event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        scrollView.setOnTouchListener(new android.view.View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
    }

    public static void LockScrollViewOnViewTouch(final RecyclerView scrollView, View view) {
        view.setOnTouchListener(new android.view.View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View p_v, MotionEvent p_event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        scrollView.setOnTouchListener(new android.view.View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
    }

    public static void setTypeFace(CheckBox checkBox, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), FontCache.REGULAR_FONT);
        checkBox.setTypeface(font);
    }

    public static void setTypeFace(RadioButton radioButton, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), FontCache.REGULAR_FONT);
        radioButton.setTypeface(font);
    }

    public static void setTypeFace(TextView textView, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), FontCache.REGULAR_FONT);
        textView.setTypeface(font);
    }

    public static void setTypeFace(Button button, Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), FontCache.REGULAR_FONT);
        button.setTypeface(font);
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
        lWindowParams.width = WindowManager.LayoutParams.FILL_PARENT; // this is where the magic happens
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

    public static void showCancelRideDialog(Context context, DriverProfileDialog profileDialog, GetEstimateBikeResponse response) {
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


        tvCash.setText(String.valueOf(response.getPrice().getCash()));
        tvSaldo.setText(String.valueOf(response.getPrice().getBalance()));
        RadioGroup radioGroup = dialog.findViewById(R.id.rdoGropu);
        rbxCash.setChecked(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rb_cash) {
                    ivxSaldo.setImageResource(R.drawable.pay_saldo);
                    ivxCash.setImageResource(R.drawable.doller_sign);

                } else if (checkedId == R.id.rb_saldo) {
                    ivxSaldo.setImageResource(R.drawable.saldo_color);
                    ivxCash.setImageResource(R.drawable.doller_sign);

                }
            }
        });


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

    public static void initializeMap(SupportMapFragment mapFragment, LatLng location) {
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {

                if (error == OnEngineInitListener.Error.NONE) {
                    map = mapFragment.getMap();
                    try {
                        map.setZoomLevel(40.0);
                    } catch (Exception e) {
                        Log.e("HERE", e.getMessage());
                    }
                    map.setZoomLevel(14.60);
                } else {
                    Log.e("error", "Cannot initialize SupportMapFragment (" + error + ")");
                }
            }
        });
    }

    public static void setDriversOnMap(GetNearestDriverResponse drives, Map map) {
        try {
            for (int i = 0; i < drives.getData().size(); i++) {
                Image image = new Image();
                image.setImageResource(R.drawable.bike);
                LatLng lt = new LatLng(Double.parseDouble(drives.getData().get(i).getLatitude()), Double.parseDouble(drives.getData().get(i).getLongitude()));
                MapMarker customMarker = new MapMarker(new GeoCoordinate(lt.latitude, lt.longitude), image);
                map.addMapObject(customMarker);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // MapMarker defaultMarker = new MapMarker();
        // defaultMarker.setCoordinate(new GeoCoordinate(Double.parseDouble(drives.getData().get(i).getLatitude()), Double.parseDouble(drives.getData().get(i).getLongitude()), 0.0));
        //map.addMapObject(defaultMarker);
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
}
