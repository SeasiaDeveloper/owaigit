package com.oway.ui.trip;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.SupportMapFragment;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.oway.App;
import com.oway.R;
import com.oway.adapters.MapPopularLocationsRecyclerAdapter;
import com.oway.base.BaseActivity;
import com.oway.callbacks.CancelButtonClick;
import com.oway.callbacks.CancelReasonDialog;
import com.oway.callbacks.DriverProfileDialog;
import com.oway.callbacks.PopularLocationsCallBack;
import com.oway.customviews.CustomTextView;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.PopularLocationsModal;
import com.oway.model.request.CancelRideRequest;
import com.oway.model.request.CustomerTransactionRequest;
import com.oway.model.request.GetCurrentLocationRequest;
import com.oway.model.request.GetEstimateBikeRequest;
import com.oway.model.request.GetNearestDriverRequest;
import com.oway.model.request.GetRecommendedPlacesRequest;
import com.oway.model.request.SendDriverRequest;
import com.oway.model.response.CancelRideResponse;
import com.oway.model.response.CustomerTransactionResponse;
import com.oway.model.response.GetEstimateBikeResponse;
import com.oway.model.response.GetNearestDriverResponse;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.model.response.LocationDetailsResponse;
import com.oway.model.response.SendDriverResponse;
import com.oway.otto.OnApplyPushNotificationEvent;
import com.oway.ui.home.MainActivity;
import com.oway.utillis.AppConstants;
import com.oway.utillis.CommonUtils;
import com.oway.utillis.Location;
import com.oway.utillis.ToastUtils;
import com.oway.utillis.ValidationUtils;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MotorTripActivity extends BaseActivity implements Location.OnLocationChangeListener, Location.OnLocationSatiListener, CancelButtonClick, DriverProfileDialog, TripActivityView, CancelReasonDialog {
    private static final String LOG_TAG = MotorTripActivity.class.getSimpleName();
    private boolean isClicked = true;

    private boolean isPickUpClick = true;
    private ArrayList<PopularLocationsModal> modalArrayList = new ArrayList<PopularLocationsModal>();
    private Map map = null;
    private SupportMapFragment mapFragment = null;
    private BottomSheetBehavior sheetBehavior;
    private Location location;
    private CancelReasonDialog reasonDialog;
    private CancelButtonClick cancelButtonClick;
    private DriverProfileDialog profileDialog;
    private final int SOURCE_SELECT = 100;
    private final int DESTINATION_SELECT = 101;
    private boolean isValid;
    private String tranxId;
    private String startAddress, startLat, startLng, endAddress, endLat, endLng,no_reason="no reason";
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
    RelativeLayout layoutBelowFloatButton;
    @BindView(R.id.ll_bottom_sheet_view)
    RelativeLayout layoutBottomSheet;
    @BindView(R.id.ll_driver_riding_to_you)
    RelativeLayout layoutDriverRidingToYou;
    @BindView(R.id.imgCurrent)
    ImageView imgCurrent;
    ImageView btnFab;
    @BindView(R.id.tv_balance)
    CustomTextView tvxBalance;

    @BindView(R.id.cencel_ride)
    Button cencel_ride;

    @BindView(R.id.btn_map_source)
    Button btn_map_source;

    @BindView(R.id.btn_map_destination)
    Button btn_map_destination;

    @Inject
    TripActivityPresenter<TripActivityView> tripActivityPresenter;

    @Inject
    ValidationUtils validationUtils;


    @OnClick(R.id.btn_cancel_ride)
    public void onCancelRideClick() {
        CommonUtils.showRideCancelReasonDialog(this, reasonDialog);
    }

    private LatLng mlocation;

    @OnClick(R.id.btn_float)
    public void onFloatButtonClick() {
        if ((sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED)) {
            btnFab.setRotation(180);
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            layoutBelowFloatButton.setVisibility(View.GONE);

        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            layoutBelowFloatButton.setVisibility(View.VISIBLE);
            btnFab.setRotation(360);
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
    }


    @OnClick(R.id.btn_map_next)
    public void onClickNextOnMap() {
        isValid = validationUtils.checkPickAndDestination(etxPickUp, etxDropDown);
        if (isValid) {
            GetEstimateBikeRequest mRequest = new GetEstimateBikeRequest();
            mRequest.setDistance(String.valueOf(CommonUtils.distance(Double.parseDouble(startLat), Double.parseDouble(startLng), Double.parseDouble(endLat), Double.parseDouble(endLng))));
            mRequest.setId_fitur(PreferenceHandler.readString(this, AppConstants.SELECTION_GRID, ""));
            mRequest.setAccess_token(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.MBR_TOKEN, ""));
            tripActivityPresenter.getEstimatePriceBike(mRequest);
        }
    }

    @OnClick(R.id.cencel_ride)
    public void onCancel() {
        CommonUtils.showCancelRide(reasonDialog);


    }

    @Override
    public void onCancelOrderClick() {

    }

    @Override
    public void onOrderClick(String price, String selection) {
        layoutPopularLocations.setVisibility(View.GONE);
        layoutSourceDestination.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        layoutPleaseWaitForRide.setVisibility(View.VISIBLE);
        imgCurrent.setVisibility(View.GONE);
        CustomerTransactionRequest mRequest = new CustomerTransactionRequest();
        mRequest.setEkl_customer(PreferenceHandler.readString(this, AppConstants.USER_ID, ""));
        mRequest.setOrder_fitur(PreferenceHandler.readString(this, AppConstants.SELECTION_GRID, ""));
        mRequest.setStart_latitude(Double.parseDouble(startLat));
        mRequest.setStart_longitude(Double.parseDouble(startLng));
        mRequest.setEnd_latitude(Double.parseDouble(endLat));
        mRequest.setEnd_longitude(Double.parseDouble(endLng));
        mRequest.setDistance(String.valueOf(CommonUtils.distance(Double.parseDouble(startLat), Double.parseDouble(startLng), Double.parseDouble(endLat), Double.parseDouble(endLng))));
        mRequest.setPrice(price);
        mRequest.setOrder_time(CommonUtils.getCurrentDateTime());
        mRequest.setPickup_address(startAddress);
        mRequest.setDestination_address(endAddress);
        mRequest.setFinal_price(price);
        mRequest.setUse_balance(selection);
        mRequest.setSeat(0);
        mRequest.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        tripActivityPresenter.getCustomerRequestTransaction(mRequest);
    }

    @OnClick(R.id.back_motor)
    public void onClick() {
        Intent intent = new Intent(MotorTripActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.civ_search)
    public void onSearchClick() {
        startSearchPlaceActivity();
    }


    @OnClick(R.id.etxPickUp)
    public void onPicUp() {
        etxPickUp.requestFocus();
        isPickUpClick = true;
        startSearchPlaceActivity();
    }

    @OnClick(R.id.etxDropDown)
    public void onDropDown() {
        etxDropDown.requestFocus();
        isPickUpClick = false;
        startSearchPlaceActivity();
    }

    private void startSearchPlaceActivity() {
        Intent intent = new Intent(MotorTripActivity.this, SearchPlacesActivity.class);
        intent.putExtra(AppConstants.LATITUDE, mlocation.latitude);
        intent.putExtra(AppConstants.LONGITUDE, mlocation.longitude);
        if (isPickUpClick)
            startActivityForResult(intent, SOURCE_SELECT);
        else
            startActivityForResult(intent, DESTINATION_SELECT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        View view = findViewById(R.id.include_sheets);
        sheetBehavior = BottomSheetBehavior.from(view);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.setPeekHeight(0);
        cancelButtonClick = this;
        profileDialog = this;
        reasonDialog = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(App.getInstance(),"call",Toast.LENGTH_SHORT).show();
            }
        }, 5000);

    }

    @Subscribe
    public void OnApplyPushNotificationEvent(OnApplyPushNotificationEvent event) {
        ToastUtils.shortToast(event.getFeature() + " " + event.getType());
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
        startLocationUpdates();
        getActivityComponent().inject(this);
        tripActivityPresenter.onAttach(MotorTripActivity.this);
        mapFragment = getSupportMapFragment();
        initializeMap();
        Intent intent = getIntent();
        tvxBalance.setText(intent.getStringExtra("balance"));
    }


    private void getNearByDriver() {
        GetNearestDriverRequest nearRequest = new GetNearestDriverRequest();
        nearRequest.setLatitude(String.valueOf(mlocation.latitude));
        nearRequest.setLongitude(String.valueOf(mlocation.longitude));
        nearRequest.setOrder_feature(PreferenceHandler.readString(this, AppConstants.SELECTION_GRID, ""));
        nearRequest.setEkl_customer(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.USER_ID, ""));
        nearRequest.setAccess_token(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.MBR_TOKEN, ""));
        tripActivityPresenter.getNearestDriver(nearRequest);
    }


    @Override
    public void onLocationChanged(LatLng location) {
        mlocation = location;
        GetCurrentLocationRequest mRequest = new GetCurrentLocationRequest();
        mRequest.setLatitude(String.valueOf(location.latitude));
        mRequest.setLongitude(String.valueOf(location.longitude));
        mRequest.setAccess_token(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.MBR_TOKEN, ""));
        tripActivityPresenter.getLocationDetails(mRequest);
        getNearByDriver();
        getRecommendedPlaces();
    }

    private void getRecommendedPlaces() {
        GetRecommendedPlacesRequest mRequest = new GetRecommendedPlacesRequest();
        mRequest.setLatitude(String.valueOf(mlocation.latitude));
        mRequest.setLongitude(String.valueOf(mlocation.longitude));
        mRequest.setEkl_customer(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.USER_ID, ""));
        mRequest.setAccess_token(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.MBR_TOKEN, ""));
        tripActivityPresenter.getRecommendedPlaces(mRequest);
    }


    void startLocationUpdates() {
        location = new Location(this);
        location.setup();
        location.setOnLocationChangeListener(this, this);
    }

    @Override
    public void onLocationSatisfied() {

    }

    @Override
    public void onGetNearestDriverResponseSuccess(GetNearestDriverResponse status) {
        CommonUtils.setDriversOnMap(status, map);

    }

    @Override
    public void onGetNearestDriverResponseFailure(String response) {
        ToastUtils.shortToast(response);
    }

    @Override
    public void onGetRecommendedPlacesSuccess(GetRecommendedPlacesResponse response) {
        for (int i = 0; i < response.getResults().size(); i++) {
            PopularLocationsModal locationsModal = new PopularLocationsModal();
            locationsModal.setLatitude(response.getResults().get(i).getGeometry().getLocation().getLat());
            locationsModal.setLongitude(response.getResults().get(i).getGeometry().getLocation().getLng());
            locationsModal.setAddress(response.getResults().get(i).getName());
            modalArrayList.add(locationsModal);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MapPopularLocationsRecyclerAdapter adapter = new MapPopularLocationsRecyclerAdapter(modalArrayList, this, new PopularLocationsCallBack() {
            @Override
            public void onItemClick(View v, String address) {
                if (isPickUpClick) {
                    etxPickUp.setText(address);
                } else {
                    etxDropDown.setText(address);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onGetRecommendedPlacesFailure(String response) {

    }

    @Override
    public void onGetAddressSuccess(Response<LocationDetailsResponse> response) {
        try {
            if (response != null) {
                etxPickUp.setText(response.body().getFormatted_address());
                startLat = String.valueOf(mlocation.latitude);
                startLng = String.valueOf(mlocation.longitude);
                startAddress = response.body().getFormatted_address();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void onGetAddressFailure(String msg) {

    }

    @Override
    public void onGetBikePriceSuccess(GetEstimateBikeResponse response) {
        CommonUtils.showCancelRideDialog(this, profileDialog, response);
    }

    @Override
    public void onGetBikePriceFailure(String response) {
        ToastUtils.shortToast(response);
    }

    @Override
    public void onGetCustomerTransactionSuccess(CustomerTransactionResponse response) {
        SendDriverRequest mRequest = new SendDriverRequest();
        mRequest.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        mRequest.setId_transaksi(String.valueOf(response.getTransaksi().getId()));
        tranxId = String.valueOf(response.getTransaksi().getId());
        tripActivityPresenter.getDriverRequest(mRequest);
    }

    @Override
    public void onGetCustomerTransactionFailure(String response) {

    }

    @Override
    public void onSendNearestDriverSuccess(SendDriverResponse response) {
        ToastUtils.shortToast(response.getRespMessage());

    }

    @Override
    public void onSendNearestDriverFailure(String response) {
        ToastUtils.shortToast(response);
    }

    @Override
    public void onCancelRideSuccess(CancelRideResponse response) {
        ToastUtils.shortToast(response.getRespMessage());

    }

    @Override
    public void onCancelRideFailure(String msg) {
        ToastUtils.shortToast(msg);
    }

    void initializeMap() {
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {

                if (error == OnEngineInitListener.Error.NONE) {
                    map = mapFragment.getMap();
                    try {
                        map.setZoomLevel(14.60);
                    } catch (Exception e) {
                        Log.e("HERE", e.getMessage());
                    }
                } else {
                    Log.e("error", "Cannot initialize SupportMapFragment (" + error + ")");
                }
            }
        });
    }


    @OnClick(R.id.btn_map_source)
    public void onSourceBtnClick() {
        etxPickUp.setText(startAddress);
        btn_map_source.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_map_destination)
    public void onDestinationBtnClick() {
        etxDropDown.setText(endAddress);
        btn_map_destination.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SOURCE_SELECT && resultCode == RESULT_OK) {
            startLat = data.getStringExtra(AppConstants.SELECT_LATITUDE);
            startLng = data.getStringExtra(AppConstants.SELECT_LONGITUDE);
            startAddress = data.getStringExtra(AppConstants.ADDRESS);
            if (!etxPickUp.getText().toString().isEmpty())
                btn_map_source.setVisibility(View.VISIBLE);
            else
                etxPickUp.setText(startAddress);

        } else if (requestCode == DESTINATION_SELECT && resultCode == RESULT_OK) {
            endLat = data.getStringExtra(AppConstants.SELECT_LATITUDE);
            endLng = data.getStringExtra(AppConstants.SELECT_LONGITUDE);
            endAddress = data.getStringExtra(AppConstants.ADDRESS);
            if (!etxDropDown.getText().toString().isEmpty())
                btn_map_destination.setVisibility(View.VISIBLE);
            else
                etxDropDown.setText(endAddress);
        }
    }

    @Override
    public void onCancelYesClick() {
        CancelRideRequest mRequest = new CancelRideRequest();
        mRequest.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        mRequest.setReason(no_reason);
        mRequest.setId_transaksi(tranxId);
        tripActivityPresenter.cancelRide(mRequest);
    }

    @Override
    public void onOkReasonDialogClick(String reason, String selectionId) {
        CancelRideRequest mRequest = new CancelRideRequest();
        mRequest.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        mRequest.setReason(reason);
        mRequest.setId_transaksi(tranxId);
        tripActivityPresenter.cancelRide(mRequest);
    }
}
