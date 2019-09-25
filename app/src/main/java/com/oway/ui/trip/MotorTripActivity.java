package com.oway.ui.trip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.SupportMapFragment;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.oway.R;
import com.oway.adapters.MapPopularLocationsRecyclerAdapter;
import com.oway.base.BaseActivity;
import com.oway.callbacks.DriverProfileDialog;
import com.oway.callbacks.PopularLocationsCallBack;
import com.oway.callbacks.CancelButtonClick;
import com.oway.model.PopularLocationsModal;
import com.oway.ui.home.MainActivity;
import com.oway.utillis.Location;
import com.oway.utillis.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MotorTripActivity extends BaseActivity implements Location.OnLocationChangeListener, Location.OnLocationSatiListener, CancelButtonClick,DriverProfileDialog {
    private static final String LOG_TAG = MotorTripActivity.class.getSimpleName();
    private boolean isClicked = true;
    private Double[] lat = {23.52437, 12.5444, 67.564656, 78.456456, 54.547646};
    private Double[] longitude = {45.76767, 78.65, 77.56656, 76.567, 56.756567};
    private String[] addresses = {"ZBXhb", "ndcjsv", "dsvbvffbfv", "dvbhdfvb", "sddsvc"};
    private ArrayList<PopularLocationsModal> modalArrayList = new ArrayList<PopularLocationsModal>();

    private Map map = null;
    private SupportMapFragment mapFragment = null;
    private BottomSheetBehavior sheetBehavior;
    private Location location;
    private CancelButtonClick cancelButtonClick;
    private DriverProfileDialog profileDialog;
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
    RelativeLayout layoutBottomSheet;
    @BindView(R.id.ll_driver_riding_to_you)
    LinearLayout layoutDriverRidingToYou;

    @OnClick(R.id.btn_cancel_ride)
    public void onCancelRideClick() {
        CommonUtils.showRideDialog(this);
    }

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
    public void onCancelRide() {
        layoutPleaseWaitForRide.setVisibility(View.GONE);
        layoutPopularLocations.setVisibility(View.VISIBLE);
        layoutSourceDestination.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        CommonUtils.showCancelDialog(this, cancelButtonClick);
    }

    @Override
    public void onCancelClick() {
        layoutPopularLocations.setVisibility(View.GONE);
        layoutSourceDestination.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        layoutBottomSheet.setVisibility(View.VISIBLE);
        layoutDriverRidingToYou.setVisibility(View.VISIBLE);
        //btnxOkDriverInfo.performClick();
    }

    @OnClick(R.id.btn_map_next)
    public void onClickNextOnMap() {
        CommonUtils.showCancelRideDialog(this, profileDialog);
    }

    @Override
    public void onCancelOrderClick() {
       // btnxCancelOrder.performClick();
    }

    @Override
    public void onOrderClick() {
        layoutPopularLocations.setVisibility(View.GONE);
        layoutSourceDestination.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        layoutPleaseWaitForRide.setVisibility(View.VISIBLE);
       // btnxOrder.performClick();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();

        View view = findViewById(R.id.include_sheets);
        sheetBehavior = BottomSheetBehavior.from(view);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.setPeekHeight(0);
        cancelButtonClick=this;
        profileDialog=this;

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
        Toast.makeText(getActivityContext(), "lee", Toast.LENGTH_SHORT).show();
    }

    private SupportMapFragment getSupportMapFragment() {
        return (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);
    }

    private void initialize() {
        setContentView(R.layout.activity_motor_trip);
        ButterKnife.bind(this);
        startLocationUpdates();
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
    public void onLocationChanged(LatLng location) {
        setCurrentLocation(location);
        Toast.makeText(getActivityContext(), String.valueOf(location.longitude), Toast.LENGTH_SHORT).show();
    }

    void startLocationUpdates() {
        location = new Location(this);
        location.setup();
        location.setOnLocationChangeListener(this, this);
    }

    @Override
    public void onLocationSatisfied() {

    }

    void setCurrentLocation(LatLng location) {
        try {
            Image image = new Image();
            image.setImageResource(R.drawable.currentlocation);
            MapMarker customMarker = new MapMarker(new GeoCoordinate(location.latitude, location.longitude, 0.0), image);
            map.addMapObject(customMarker);
        } catch (Exception e) {
            Log.e("HERE", e.getMessage());
        }
    }


}
