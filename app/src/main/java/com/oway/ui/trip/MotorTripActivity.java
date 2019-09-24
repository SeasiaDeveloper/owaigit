package com.oway.ui.trip;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.SupportMapFragment;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.oway.R;
import com.oway.adapters.MapPopularLocationsRecyclerAdapter;
import com.oway.base.BaseActivity;
import com.oway.callbacks.PopularLocationsCallBack;
import com.oway.model.PopularLocationsModal;
import com.oway.ui.home.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MotorTripActivity extends BaseActivity {
    private static final String LOG_TAG = MotorTripActivity.class.getSimpleName();
    private boolean isClicked = true;
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private Double[] lat = {23.52437, 12.5444, 67.564656, 78.456456, 54.547646};
    private Double[] longitude = {45.76767, 78.65, 77.56656, 76.567, 56.756567};
    private String[] addresses = {"ZBXhb", "ndcjsv", "dsvbvffbfv", "dvbhdfvb", "sddsvc"};
    private ArrayList<PopularLocationsModal> modalArrayList = new ArrayList<PopularLocationsModal>();

    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Map map = null;
    private SupportMapFragment mapFragment = null;
    private BottomSheetBehavior sheetBehavior;

    @BindView(R.id.popular_location)
    RecyclerView recyclerView;
    @BindView(R.id.etxPickUp)
    EditText etxPickUp;
    @BindView(R.id.etxDropDown)
    EditText etxDropDown;
    @BindView(R.id.back_motor)
    CircularImageView backButton;
    @BindView(R.id.civ_search)
    CircularImageView civxSearch;
    @BindView(R.id.ll_recycler_location)
    LinearLayout layoutPopularLocations;
    @BindView(R.id.ll_locations)
    LinearLayout layoutSourceDestination;
    @BindView(R.id.ll_please_wait)
    LinearLayout layoutPleaseWaitForRide;
    @BindView(R.id.ll_below_float_btn)
    LinearLayout layoutBelowFloatButton;
    @BindView(R.id.ll_bottom_sheet_view)
    LinearLayout layoutBottomSheet;
    @BindView(R.id.ll_driver_riding_to_you)
    LinearLayout layoutDriverRidingToYou;

    @OnClick(R.id.btn_float)
    public void onFloatButtonClick() {
        if ((sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED)) {
            layoutBelowFloatButton.setVisibility(View.GONE);
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            layoutBelowFloatButton.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.cencel_ride)
    public void onCencelRide() {
        layoutPleaseWaitForRide.setVisibility(View.GONE);
        layoutPopularLocations.setVisibility(View.VISIBLE);
        layoutSourceDestination.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        Dialog dialog = new Dialog(this, R.style.CustomAlertDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.you_got_driver_dialog_box);
        Button btnxOkOnDriverInfo = dialog.findViewById(R.id.btn_ok_driver_info);
        btnxOkOnDriverInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPopularLocations.setVisibility(View.GONE);
                layoutSourceDestination.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                layoutBottomSheet.setVisibility(View.VISIBLE);
                layoutDriverRidingToYou.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @OnClick(R.id.btn_map_next)
    public void onClickNextOnMap() {
        Dialog dialog = new Dialog(this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.map_next_button_dialog_box);
        Button btnxOrder = dialog.findViewById(R.id.btn_order);
        Button btnxCencelOrder = dialog.findViewById(R.id.btn_cencel_order);
        btnxCencelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        btnxOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPopularLocations.setVisibility(View.GONE);
                layoutSourceDestination.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                layoutPleaseWaitForRide.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @OnClick(R.id.back_motor)
    public void onClick() {
        Intent intent = new Intent(MotorTripActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @OnClick(R.id.civ_search)
    public void onSearchClick() {
        Intent intent = new Intent(MotorTripActivity.this, SearchPlaces.class);
        startActivity(intent);
    }

    @OnTouch(R.id.etxPickUp)
    public void onPicUpTouch() {
        etxPickUp.requestFocus();  //keep focus on the EditText(redTime)
        isClicked = true;
    }

    @OnTouch(R.id.etxDropDown)
    public void onDropDown() {
        etxDropDown.requestFocus();  //keep focus on the EditText(redTime)
        isClicked = false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions();

        View includeView = findViewById(R.id.include_sheet);
        sheetBehavior = BottomSheetBehavior.from(includeView);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.setPeekHeight(0);

        for (int i = 0; i <= 4; i++) {
            PopularLocationsModal locationsModal = new PopularLocationsModal();
            locationsModal.setLatitude(lat[i]);
            locationsModal.setLongitude(longitude[i]);
            locationsModal.setAddress(addresses[i]);
            modalArrayList.add(locationsModal);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MapPopularLocationsRecyclerAdapter adapter = new MapPopularLocationsRecyclerAdapter(modalArrayList, this, new PopularLocationsCallBack() {
            @Override
            public void onItemClick(View v, String address) {
                if (isClicked) {
                    etxPickUp.setText(address);
                } else {
                    etxDropDown.setText(address);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setUp() {

    }

    private SupportMapFragment getSupportMapFragment() {
        return (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);
    }

    private void initialize() {
        setContentView(R.layout.activity_motor_trip);
        ButterKnife.bind(this);


        mapFragment = getSupportMapFragment();
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    map = mapFragment.getMap();


                    PositioningManager positioningManager = PositioningManager.getInstance();
                    positioningManager.start(PositioningManager.LocationMethod.GPS_NETWORK);
                   /* GeoPosition position = positioningManager.getPosition();
                    GeoCoordinate coordinate = position.getCoordinate();*/

                    map.setCenter(new GeoCoordinate(54.2343, 45.656, 12), Map.Animation.LINEAR);
                    map.getPositionIndicator().setVisible(true);


                } else {
                    Log.e(LOG_TAG, "Cannot initialize SupportMapFragment (" + error + ")");
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                // all permissions were granted
                initialize();
                break;
        }
    }


    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }
}
