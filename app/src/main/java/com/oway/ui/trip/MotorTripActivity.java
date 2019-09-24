package com.oway.ui.trip;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @OnClick(R.id.back_motor)
    public void onClick() {
        Intent intent = new Intent(MotorTripActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions();

        for (int i = 0; i <= 4; i++) {
            PopularLocationsModal locationsModal = new PopularLocationsModal();
            locationsModal.setLatitude(lat[i]);
            locationsModal.setLongitude(longitude[i]);
            locationsModal.setAddress(addresses[i]);
            modalArrayList.add(locationsModal);
        }

        etxPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isClicked = true;


            }
        });
        etxDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isClicked = false;


            }
        });

        civxSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotorTripActivity.this, SearchPlaces.class);
                startActivity(intent);
            }
        });

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
