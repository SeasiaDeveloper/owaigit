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

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
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
import com.oway.callbacks.TermsAndConditionCallBack;
import com.oway.customviews.CustomTextView;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.PopularLocationsModal;
import com.oway.model.VehicleTypeModal;
import com.oway.model.request.CalculateRouteRequest;
import com.oway.model.request.CancelRideRequest;
import com.oway.model.request.CustomerTransactionRequest;
import com.oway.model.request.GetCurrentLocationRequest;
import com.oway.model.request.GetEstimateBikeRequest;
import com.oway.model.request.GetNearestDriverRequest;
import com.oway.model.request.GetPriceBySeatRequest;
import com.oway.model.request.GetRecommendedPlacesRequest;
import com.oway.model.request.SendDriverRequest;
import com.oway.model.response.CalculateRouteResponse;
import com.oway.model.response.CancelRideResponse;
import com.oway.model.response.CustomerTransactionResponse;
import com.oway.model.response.GetEstimateBikeResponse;
import com.oway.model.response.GetNearestDriverResponse;
import com.oway.model.response.GetPriceBySeatResponse;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.model.response.LocationDetailsResponse;
import com.oway.model.response.SendDriverResponse;
import com.oway.otto.BusProvider;
import com.oway.otto.OnApplyPushNotificationEventArrived;
import com.oway.otto.OnApplyPushNotificationEventArrivingNow;
import com.oway.otto.OnApplyPushNotificationEventTripStart;
import com.oway.ui.home.MainActivity;
import com.oway.utillis.AppConstants;
import com.oway.utillis.CommonUtils;
import com.oway.utillis.Location;
import com.oway.utillis.Logger;
import com.oway.utillis.ToastUtils;
import com.oway.utillis.ValidationUtils;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MotorTripActivity extends BaseActivity implements Location.OnLocationChangeListener, Location.OnLocationSatiListener, CancelButtonClick, DriverProfileDialog, TripActivityView, CancelReasonDialog, TermsAndConditionCallBack {
    private static final String LOG_TAG = MotorTripActivity.class.getSimpleName();
    private boolean isClicked = true;
    private OnApplyPushNotificationEventArrivingNow mEvent;
    private OnApplyPushNotificationEventArrived mEventArriving;

    private boolean isPickUpClick = true;
    private ArrayList<PopularLocationsModal> modalArrayList = new ArrayList<PopularLocationsModal>();
    private Map map = null;
    private SupportMapFragment mapFragment = null;
    private BottomSheetBehavior sheetBehavior;
    private Location location;
    private CancelReasonDialog reasonDialog;
    private CancelButtonClick cancelButtonClick;
    private TermsAndConditionCallBack termsAndConditionCallBack;
    private DriverProfileDialog profileDialog;
    private final int SOURCE_SELECT = 100;
    private final int DESTINATION_SELECT = 101;
    private boolean isValid;
    private String tranxId;
    private String startAddress, startLat, startLng, endAddress, endLat, endLng, no_reason = "no reason";
    private ArrayList<VehicleTypeModal> vehicleTypeModalArrayList = new ArrayList<>();
    private int seat = 0;
    private Handler handler;
    private VehicleTypeModal vehicleTypeModal = new VehicleTypeModal();
    private GetPriceBySeatResponse mResponse;
    private Runnable myRunnable;
    private MapMarker mapMarker_source;
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
    @BindView(R.id.btn_float)
    ImageView btnFab;
    @BindView(R.id.btn_float_loc)
    ImageView btnxLocFloat;
    @BindView(R.id.tv_balance)
    CustomTextView tvxBalance;
    @BindView(R.id.rv_vehicle_types)
    RecyclerView rvxVehicleTypes;
    @BindView(R.id.cencel_ride)
    Button cencel_ride;
    @BindView(R.id.btn_map_next)
    Button btn_map_next;
    @BindView(R.id.ll_search_locs)
    LinearLayout llxSearchLocs;
    @BindView(R.id.btn_map_source)
    Button btn_map_source;
    @BindView(R.id.ll_trip_info)
    LinearLayout llxTripInfo;
    @BindView(R.id.btn_map_destination)
    Button btn_map_destination;
    @BindView(R.id.tv_trip_distance)
    CustomTextView tvxTripDistance;
    @BindView(R.id.tv_traffic_time)
    CustomTextView tvxTrafficTime;


    @Inject
    TripActivityPresenter<TripActivityView> tripActivityPresenter;

    @Inject
    ValidationUtils validationUtils;

    @BindView(R.id.ll_bottom_sheet_loc)
    RelativeLayout layoutTripStart;

    @OnClick(R.id.btn_cancel_ride)
    public void onCancelRideClick() {
        CommonUtils.showRideCancelReasonDialog(this, reasonDialog);
    }

    private LatLng mlocation;

    @OnClick(R.id.ib_call_driver)
    public void onCall() {
        CommonUtils.callDriver("event.getDriver_phone()", null);
    }

    @OnClick(R.id.ib_call_driver_bottom_sheet)
    public void onCallFromBottomSheet() {
        CommonUtils.callDriver("event.getDriver_phone()", null);
    }

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

    @OnClick(R.id.btn_float_loc)
    public void onButtonClick() {
        if ((sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED)) {
            btnxLocFloat.setRotation(180);
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            layoutBelowFloatButton.setVisibility(View.GONE);

        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            layoutBelowFloatButton.setVisibility(View.VISIBLE);
            btnxLocFloat.setRotation(360);
        }
    }

    @OnClick(R.id.civ_help_alart)
    public void onHelpClick() {
        CommonUtils.showPopUpWindow(this, MotorTripActivity.this, layoutTripStart, termsAndConditionCallBack);
    }
    /*@OnClick(R.id.cencel_ride)
    public void onCancelRide() {
        layoutPleaseWaitForRide.setVisibility(View.GONE);
        layoutPopularLocations.setVisibility(View.VISIBLE);
        layoutSourceDestination.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        CommonUtils.showCancelDialog(this, cancelButtonClick);
    }*/

    @Override
    public void onCancelClick() {
        layoutPopularLocations.setVisibility(View.GONE);
        layoutSourceDestination.setVisibility(View.GONE);
        layoutBottomSheet.setVisibility(View.VISIBLE);
        layoutDriverRidingToYou.setVisibility(View.VISIBLE);
        CommonUtils.setDrivingArrivingData(layoutDriverRidingToYou, mEvent);
        CommonUtils.setBottomSheetData(this, layoutBottomSheet, mEvent);
    }


    @OnClick(R.id.btn_map_next)
    public void onClickNextOnMap() {
        llxTripInfo.setVisibility(View.GONE);
        if (PreferenceHandler.readString(this, AppConstants.SELECTION_GRID, "").equals("1")) {
            isValid = validationUtils.checkPickAndDestination(etxPickUp, etxDropDown);
            if (isValid) {
                GetEstimateBikeRequest mRequest = new GetEstimateBikeRequest();
                mRequest.setDistance(String.valueOf(CommonUtils.distance(Double.parseDouble(startLat), Double.parseDouble(startLng), Double.parseDouble(endLat), Double.parseDouble(endLng))));
                mRequest.setId_fitur(PreferenceHandler.readString(this, AppConstants.SELECTION_GRID, ""));
                mRequest.setAccess_token(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.MBR_TOKEN, ""));
                tripActivityPresenter.getEstimatePriceBike(mRequest);
            }
        } else if (PreferenceHandler.readString(this, AppConstants.SELECTION_GRID, "").equals("2")) {
            if (mResponse == null) {
                GetPriceBySeatRequest mRequestPrice = new GetPriceBySeatRequest();
                mRequestPrice.setLatitude(String.valueOf(mlocation.latitude));
                mRequestPrice.setLongitude(String.valueOf(mlocation.longitude));
                mRequestPrice.setDistance(String.valueOf(CommonUtils.distance(Double.parseDouble(startLat), Double.parseDouble(startLng), Double.parseDouble(endLat), Double.parseDouble(endLng))));
                mRequestPrice.setAccess_token(PreferenceHandler.readString(MotorTripActivity.this, AppConstants.MBR_TOKEN, ""));
                tripActivityPresenter.getPriceBySeat(mRequestPrice);
            } else {
                boolean selectCar = false;
                for (int i = 0; i < vehicleTypeModalArrayList.size(); i++) {
                    if (vehicleTypeModalArrayList.get(i).isSelect()) {
                        seat = Integer.parseInt(vehicleTypeModalArrayList.get(i).getNoOfSeats());
                        selectCar = true;
                    }
                }
                if (selectCar)
                    CommonUtils.showCancelRideDialog(this, profileDialog, mResponse.getData().get(1).getCash(), mResponse.getData().get(1).getBalance());
                else
                    ToastUtils.shortToast(getResources().getString(R.string.seat_validation));
            }
        }

    }

    @OnClick(R.id.cencel_ride)
    public void onCancel() {
        CommonUtils.showCancelRide(reasonDialog, this);

    }

    @Override
    public void onCancelOrderClick() {

    }

    @Override
    public void onOrderClick(String price, String selection) {

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
        mRequest.setSeat(seat);
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
        isPickUpClick = true;

        if (llxTripInfo.getVisibility() == View.VISIBLE) {
            llxTripInfo.setVisibility(View.GONE);
            llxSearchLocs.setVisibility(View.VISIBLE);

        } else {
            etxPickUp.requestFocus();
            startSearchPlaceActivity();
        }
    }

    @OnClick(R.id.etxDropDown)
    public void onDropDown() {
        isPickUpClick = false;
        if (llxTripInfo.getVisibility() == View.VISIBLE) {
            llxTripInfo.setVisibility(View.GONE);
            llxSearchLocs.setVisibility(View.VISIBLE);
        } else {
            etxDropDown.requestFocus();
            startSearchPlaceActivity();
        }
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
        BusProvider.getInstance().register(this);
        View view = findViewById(R.id.include_sheets);
        sheetBehavior = BottomSheetBehavior.from(view);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.setPeekHeight(0);
        View viewWithLoc = findViewById(R.id.include_sheets_loc);
        sheetBehavior = BottomSheetBehavior.from(viewWithLoc);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.setPeekHeight(0);
        cancelButtonClick = this;
        profileDialog = this;
        reasonDialog = this;
        termsAndConditionCallBack = (TermsAndConditionCallBack) this;

    }

    @Subscribe
    public void OnApplyPushNotificationEventArrivingNow(OnApplyPushNotificationEventArrivingNow event) {
        CommonUtils.showCancelDialog(this, cancelButtonClick, event);
        mEvent = event;
    }

    @Subscribe
    public void OnApplyPushNotificationEventArrived(OnApplyPushNotificationEventArrived event) {
        mEvent = CommonUtils.cloneObject(event);
        layoutSourceDestination.setVisibility(View.GONE);
        layoutPopularLocations.setVisibility(View.GONE);
        layoutBottomSheet.setVisibility(View.VISIBLE);
        layoutDriverRidingToYou.setVisibility(View.VISIBLE);
        CommonUtils.setDrivingArrivingData(layoutDriverRidingToYou, mEvent);
        CommonUtils.setBottomSheetData(this, layoutBottomSheet, mEvent);
    }

    @Subscribe
    public void OnApplyPushNotificationEventTripStart(OnApplyPushNotificationEventTripStart event) {
        CommonUtils.setTripStartData(this, layoutTripStart, event);
        layoutSourceDestination.setVisibility(View.GONE);
        layoutPopularLocations.setVisibility(View.GONE);
        layoutBottomSheet.setVisibility(View.GONE);
        layoutDriverRidingToYou.setVisibility(View.GONE);
        layoutTripStart.setVisibility(View.VISIBLE);
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
        tvxBalance.setText(intent.getStringExtra(AppConstants.BALANCE));
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
        if(tripActivityPresenter!=null) {
            tripActivityPresenter.getLocationDetails(mRequest);
            getNearByDriver();
            getRecommendedPlaces();
        }
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

    private void setOneMinuteHandler() {
         handler= new Handler();
         myRunnable= new Runnable() {
            public void run() {
                cancelRide();
            }
        };
        handler.postDelayed(myRunnable, 3000);
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
            public void onItemClick(View v, String address, String lat, String lng) {
                if (isPickUpClick) {
                    startLat = lat;
                    startLng = lng;
                    etxPickUp.setText(address);
                } else {
                    endLat = lat;
                    endLng = lng;
                    etxDropDown.setText(address);
                }
                if (startLng != null && endLng != null)
                    hitApiGetRoute();

                if(etxDropDown.getText().toString().isEmpty())
                {
                    map.removeMapObject(mapMarker_source);
                    addCurrentMarker();
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
                addCurrentMarker();
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void addCurrentMarker() {
        Image image = new Image();
        try {

            image.setImageResource(R.drawable.source_mark);
            mapMarker_source= new MapMarker(new GeoCoordinate(Double.valueOf(startLat), Double.valueOf(startLng), 0.0), image);
            Objects.requireNonNull(map).addMapObject(mapMarker_source);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onGetAddressFailure(String msg) {

    }

    @Override
    public void onGetBikePriceSuccess(GetEstimateBikeResponse response) {
        CommonUtils.showCancelRideDialog(this, profileDialog, response.getPrice().getCash(), response.getPrice().getBalance());
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
        layoutPopularLocations.setVisibility(View.GONE);
        layoutSourceDestination.setVisibility(View.GONE);
        layoutPleaseWaitForRide.setVisibility(View.VISIBLE);
        setOneMinuteHandler();

    }

    @Override
    public void onSendNearestDriverFailure(String response) {
        ToastUtils.shortToast(response);
    }

    @Override
    public void onCancelRideSuccess(CancelRideResponse response) {
        ToastUtils.shortToast(response.getRespMessage());
        layoutPopularLocations.setVisibility(View.VISIBLE);
        layoutSourceDestination.setVisibility(View.VISIBLE);
        layoutPleaseWaitForRide.setVisibility(View.GONE);
        layoutBottomSheet.setVisibility(View.GONE);
        layoutDriverRidingToYou.setVisibility(View.GONE);

    }

    @Override
    public void onCancelRideFailure(String msg) {
        ToastUtils.shortToast(msg);

    }

    @Override
    public void onGetPriceBySeatSuccess(GetPriceBySeatResponse response) {
        mResponse = response;
        for (int i = 0; i < response.getData().size(); i++) {
            vehicleTypeModal = new VehicleTypeModal();
            vehicleTypeModal.setCarImage(R.drawable.car);
            vehicleTypeModal.setNoOfSeats(String.valueOf(response.getData().get(i).getSeat()));
            vehicleTypeModal.setNoOfPeople(String.valueOf(response.getData().get(i).getDescription()));
            vehicleTypeModal.setAmountToPay(String.valueOf(response.getData().get(i).getCash()));
            vehicleTypeModalArrayList.add(vehicleTypeModal);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvxVehicleTypes.setLayoutManager(layoutManager);
        VehicleTypesAdapter adapter = new VehicleTypesAdapter(vehicleTypeModalArrayList, this);
        rvxVehicleTypes.setAdapter(adapter);
        rvxVehicleTypes.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetPriceBySeatFailure(String response) {

    }

    @Override
    public void onCalculateRouteSuccess(CalculateRouteResponse response) {

        tvxTripDistance.setText(String.valueOf(response.getData().getDistance()+" KM"));
        tvxTrafficTime.setText(String.valueOf(response.getData().getTrafficTime()+" Jam"));
        llxSearchLocs.setVisibility(View.GONE);
        llxTripInfo.setVisibility(View.VISIBLE);
        CommonUtils.getDirections(map, Double.valueOf(startLat), Double.valueOf(startLng), Double.valueOf(endLat), Double.valueOf(endLng));

    }

    @Override
    public void onCalculateRouteFailure(String response) {

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
          /*  if (!etxPickUp.getText().toString().isEmpty())
                btn_map_source.setVisibility(View.VISIBLE);
            else*/
            etxPickUp.setText(startAddress);

        } else if (requestCode == DESTINATION_SELECT && resultCode == RESULT_OK) {
            endLat = data.getStringExtra(AppConstants.SELECT_LATITUDE);
            endLng = data.getStringExtra(AppConstants.SELECT_LONGITUDE);
            endAddress = data.getStringExtra(AppConstants.ADDRESS);
         /*   if (!etxDropDown.getText().toString().isEmpty())
                btn_map_destination.setVisibility(View.VISIBLE);
            else*/
            etxDropDown.setText(endAddress);
        }

        if (!etxPickUp.getText().toString().isEmpty() && !etxDropDown.getText().toString().isEmpty()) {
            CommonUtils.getDirections(map, Double.valueOf(startLat), Double.valueOf(startLng), Double.valueOf(endLat), Double.valueOf(endLng));
            btn_map_next.setEnabled(true);
            llxSearchLocs.setVisibility(View.GONE);
            llxTripInfo.setVisibility(View.VISIBLE);
            btn_map_next.setBackgroundColor(getResources().getColor(R.color.col_orange));
        }
        if(etxDropDown.getText().toString().isEmpty())
        {
            map.removeMapObject(mapMarker_source);
            addCurrentMarker();
        }
        else {
            map.removeMapObject(mapMarker_source);
        }
        hitApiGetRoute();
    }

    public void hitApiGetRoute() {
        CalculateRouteRequest mRequest = new CalculateRouteRequest();
        mRequest.setLat1(startLat);
        mRequest.setLon1(startLng);
        mRequest.setLat2(endLat);
        mRequest.setLon2(endLng);
        mRequest.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        tripActivityPresenter.getCalculateRoute(mRequest);
    }

    @Override
    public void onCancelYesClick() {
        // etxPickUp.setText("");
       /* etxDropDown.setText("");
        btn_map_next.setEnabled(false);
        btn_map_next.setBackgroundColor(getResources().getColor(R.color.col_gray));*/
        manageVisibility();
        rvxVehicleTypes.setVisibility(View.GONE);
        mResponse = null;
        cancelRide();
    }

    private void cancelRide() {

        CancelRideRequest mRequest = new CancelRideRequest();
        mRequest.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        mRequest.setReason(no_reason);
        mRequest.setId_transaksi(tranxId);
        tripActivityPresenter.cancelRide(mRequest);
    }

    @Override
    public void onOkReasonDialogClick(String reason, String selectionId) {
        if (tranxId == null)
            tranxId = String.valueOf(mEvent.getId_transaksi());
        manageVisibility();
        CancelRideRequest mRequest = new CancelRideRequest();
        mRequest.setAccess_token(PreferenceHandler.readString(this, AppConstants.MBR_TOKEN, ""));
        mRequest.setReason(reason);
        mRequest.setId_transaksi(tranxId);
        tripActivityPresenter.cancelRide(mRequest);
    }

    private void manageVisibility() {
        // etxPickUp.setText("");
        etxDropDown.setText("");
        btn_map_next.setEnabled(false);
        btn_map_next.setBackgroundColor(getResources().getColor(R.color.col_gray));

    }

    public static void startOnclick(String balance, FragmentActivity activity) {
        Intent intent = new Intent(App.getInstance(), MotorTripActivity.class);
        intent.putExtra(AppConstants.BALANCE, balance);
        activity.startActivity(intent);
    }

    @Override
    public void onCancelConditionClick() {

    }

    @Override
    public void onYesClick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        tripActivityPresenter.onDetach();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(handler!=null)
        handler.removeCallbacks(myRunnable);
    }
}
