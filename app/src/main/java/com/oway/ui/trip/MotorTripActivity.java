package com.oway.ui.trip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.SupportMapFragment;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.oway.R;
import com.oway.adapters.MapPopularLocationsRecyclerAdapter;
import com.oway.base.BaseActivity;
import com.oway.callbacks.CancelButtonClick;
import com.oway.callbacks.DriverProfileDialog;
import com.oway.callbacks.PopularLocationsCallBack;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.PopularLocationsModal;
import com.oway.model.request.CustomerTransactionRequest;
import com.oway.model.request.GetEstimateBikeRequest;
import com.oway.model.request.GetNearestDriverRequest;
import com.oway.model.request.GetRecommendedPlacesRequest;
import com.oway.model.request.SendDriverRequest;
import com.oway.model.response.CustomerTransactionResponse;
import com.oway.model.response.GetEstimateBikeResponse;
import com.oway.model.response.GetNearestDriverResponse;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.model.response.LocationDetailsResponse;
import com.oway.otto.BusProvider;
import com.oway.ui.home.MainActivity;
import com.oway.utillis.AppConstants;
import com.oway.utillis.CommonUtils;
import com.oway.utillis.Location;
import com.oway.utillis.ToastUtils;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import retrofit2.Response;

public class MotorTripActivity extends BaseActivity implements Location.OnLocationChangeListener, Location.OnLocationSatiListener, CancelButtonClick, DriverProfileDialog, TripActivityView {
    private boolean isPickUpClick = true;
    private ArrayList<PopularLocationsModal> modalArrayList = new ArrayList<PopularLocationsModal>();
    private LatLng latLngStart;
    private Map map = null;
    private SupportMapFragment mapFragment = null;
    private BottomSheetBehavior sheetBehavior;
    private Location location;
    private CancelButtonClick cancelButtonClick;
    private DriverProfileDialog profileDialog;
    private List<LocationDetailsResponse.ResultsBean.AddressComponentsBean> localityName;
    private final int SOURCE_SELECT = 100;
    private final int DESTINATION_SELECT = 101;

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

    @BindView(R.id.btn_map_source)
    Button btn_map_source;

    @BindView(R.id.btn_map_destination)
    Button btn_map_destination;

    @Inject
    TripActivityPresenter<TripActivityView> tripActivityPresenter;


    @OnClick(R.id.btn_cancel_ride)
    public void onCancelRideClick() {
        CommonUtils.showRideDialog(this);
    }

    private LatLng mlocation;

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
    }

    @Subscribe
    public void OnSelectLocation(String loc) {
        ToastUtils.shortToast(loc);

    }

    @OnClick(R.id.btn_map_next)
    public void onClickNextOnMap() {
        GetEstimateBikeRequest mRequest = new GetEstimateBikeRequest();
        mRequest.setDistance("2");
        mRequest.setId_fitur(PreferenceHandler.readString(this, AppConstants.SELECTION_GRID, ""));
        mRequest.setAccess_token(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.MBR_TOKEN, ""));
        tripActivityPresenter.getEstimatePriceBike(mRequest);
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

        CustomerTransactionRequest mRequet = new CustomerTransactionRequest();
        mRequet.setEkl_customer(PreferenceHandler.readString(this, AppConstants.USER_ID, ""));
        mRequet.setOrder_fitur(PreferenceHandler.readString(this, AppConstants.SELECTION_GRID, ""));
        mRequet.setStart_latitude(Double.parseDouble("-7.5453971"));
        mRequet.setStart_longitude(Double.parseDouble("112.2417105"));
        mRequet.setEnd_latitude(Double.parseDouble("7.0707256907261"));
        mRequet.setEnd_longitude(Double.parseDouble("112.37143334001"));
        mRequet.setDistance("1");
        mRequet.setPrice(price);
        mRequet.setOrder_time(CommonUtils.getCurrentDateTime());
        mRequet.setPickup_address("lamongan");
        mRequet.setDestination_address("turi");
        mRequet.setFinal_price(price);
        mRequet.setUse_balance(selection);
        mRequet.setSeat(0);
        mRequet.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        tripActivityPresenter.getCustomerRequestTransaction(mRequet);


    }

    @OnClick(R.id.back_motor)
    public void onClick() {
        Intent intent = new Intent(MotorTripActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.civ_search)
    public void onSearchClick() {
        Intent intent = new Intent(this, SearchPlaces.class);
        intent.putExtra(AppConstants.LATITUDE, mlocation.latitude);
        intent.putExtra(AppConstants.LONGITUDE, mlocation.longitude);
        if (isPickUpClick)
            startActivityForResult(intent, SOURCE_SELECT);
        else
            startActivityForResult(intent, DESTINATION_SELECT);

    }


    @OnTouch(R.id.etxPickUp)
    public void onPicUpTouch() {
        etxPickUp.requestFocus();  //keep focus on the EditText(redTime)
        isPickUpClick = true;
        Intent intent = new Intent(MotorTripActivity.this, SearchPlaces.class);
        intent.putExtra(AppConstants.LATITUDE, mlocation.latitude);
        intent.putExtra(AppConstants.LONGITUDE, mlocation.longitude);
        startActivityForResult(intent, AppConstants.REQUEST_CODE_PICK);
    }

    @OnTouch(R.id.etxDropDown)
    public void onDropDown() {
        etxDropDown.requestFocus();  //keep focus on the EditText(redTime)
        isPickUpClick = false;
        Intent intent = new Intent(MotorTripActivity.this, SearchPlaces.class);
        intent.putExtra(AppConstants.LATITUDE, mlocation.latitude);
        intent.putExtra(AppConstants.LONGITUDE, mlocation.longitude);
        startActivityForResult(intent, AppConstants.REQUEST_CODE_DROP);
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
        BusProvider.getInstance().register(this);
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
        tripActivityPresenter.getLocationDetails(mlocation.latitude + "," + mlocation.longitude, getResources().getString(R.string.google_key));
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
        ToastUtils.shortToast("Driver found");
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
                StringBuilder localityAddress = new StringBuilder();
                localityName = response.body().getResults().get(1).getAddress_components();
                localityAddress.append(localityName.get(1).getLong_name()).append(", ");
                for (int i = 0; i < localityName.size(); i++) {
                    if (localityName.get(i).getTypes().contains("locality")) {
                        localityAddress.append(localityName.get(i).getShort_name());
                    }
                }
                latLngStart = location.getLocation();
                // latlongs.add(latLngStart);
                etxPickUp.setText(localityAddress);
            }
        } catch (Exception e) {
            Log.e("null", "null");
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
        ToastUtils.shortToast(response.getErrNumber());
        SendDriverRequest mRequest = new SendDriverRequest();
        mRequest.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        mRequest.setId_transaksi(String.valueOf(response.getTransaksi().getId()));
        tripActivityPresenter.getDriverRequest(mRequest);
    }

    @Override
    public void onGetCustomerTransactionFailure(String response) {

    }

    @Override
    public void onGetNearestDriverSuccess(GetNearestDriverResponse response) {
        ToastUtils.shortToast(response.getRespMessage());
    }

    @Override
    public void onGetNearestDriverFailure(String response) {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SOURCE_SELECT && resultCode == RESULT_OK) {
            String result = data.getStringExtra(AppConstants.SELECT_LONGITUDE);
            String result1 = data.getStringExtra(AppConstants.SELECT_LATITUDE);
            String result2 = data.getStringExtra(AppConstants.ADDRESS);
            ToastUtils.shortToast("souce "+result+" "+result1+" "+result2);
        } else if (requestCode == DESTINATION_SELECT && resultCode == RESULT_OK) {
            ToastUtils.shortToast("DESSTIMN");
        }
    }
}
