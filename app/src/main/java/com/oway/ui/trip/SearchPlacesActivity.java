package com.oway.ui.trip;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.datasource.pref.PreferenceHandler;
import com.oway.model.request.GetSearchPlacesRequest;
import com.oway.model.response.GetRecommendedPlacesResponse;
import com.oway.utillis.AppConstants;
import com.oway.utillis.ToastUtils;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPlacesActivity extends BaseActivity implements SearchPlacesView {

    @BindView(R.id.et_place_name)
    AutoCompleteTextView etxPlaces;
    @BindView(R.id.ib_search_back)
    ImageButton ibxSearchBack;
    @BindView(R.id.rv_places_list)
    RecyclerView rvxPlaceList;

    @BindView(R.id.tv_no_record)
    TextView tv_no_record;

    @BindView(R.id.pBar)
    ProgressBar pBar;
    double latitude;
    double longitude;
    private Intent intent;

    private ArrayList<GetRecommendedPlacesResponse.ResultsBean> placesResponses = new ArrayList<GetRecommendedPlacesResponse.ResultsBean>();
    @Inject
     SearchPlacesPresenter<SearchPlacesView> searchPlacesPresenter;
    private PlacesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_places);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        searchPlacesPresenter.onAttach(this);
        intent = getIntent();
        latitude = intent.getDoubleExtra(AppConstants.LATITUDE, 0);
        longitude = intent.getDoubleExtra(AppConstants.LONGITUDE, 0);
        adapter = new PlacesAdapter(placesResponses, SearchPlacesActivity.this);
        setListner();
        placesList();
        getLocation("");
    }

    @OnClick(R.id.ib_search_back)
    public void onSearchBackClick() {
        finish();
    }

    private void setListner() {
        etxPlaces.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pBar.setVisibility(View.VISIBLE);
                rvxPlaceList.setVisibility(View.GONE);
                getLocation(Objects.requireNonNull(etxPlaces.getText()).toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getLocation(String location) {
        GetSearchPlacesRequest request = new GetSearchPlacesRequest();
        request.setLatitude(String.valueOf(latitude));
        request.setLongitude(String.valueOf(longitude));
        request.setQuery(Objects.requireNonNull(location));
        request.setAccess_token(PreferenceHandler.readString(SearchPlacesActivity.this, AppConstants.MBR_TOKEN, ""));
        searchPlacesPresenter.getSearchPlaces(request);
    }

    public void placesList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvxPlaceList.setLayoutManager(layoutManager);
        rvxPlaceList.setAdapter(adapter);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onGetSearchPlacesResponseSuccess(GetRecommendedPlacesResponse status) {
        placesResponses.clear();
        for (int i = 0; i < status.getResults().size(); i++) {
            placesResponses.addAll(status.getResults());
        }
        pBar.setVisibility(View.GONE);
        if (placesResponses.size() <= 0) {
            rvxPlaceList.setVisibility(View.GONE);
            tv_no_record.setVisibility(View.VISIBLE);
        } else {
            rvxPlaceList.setVisibility(View.VISIBLE);
            tv_no_record.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetsearchPlacesResponseFailure(String response) {
        ToastUtils.shortToast(response);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
