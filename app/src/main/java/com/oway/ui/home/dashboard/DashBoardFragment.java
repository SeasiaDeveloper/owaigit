package com.oway.ui.home.dashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.oway.R;
import com.oway.adapters.DashboardRecyclerAdapter;
import com.oway.base.BaseFragment;
import com.oway.callbacks.DashbordRecyclerItemclick;
import com.oway.model.DashboardGridItemsModal;
import com.oway.ui.home.MainActivity;
import com.oway.ui.trip.MotorTripActivity;
import com.oway.utillis.GlideImageLoader;
import com.yyydjk.library.BannerLayout;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class DashBoardFragment extends BaseFragment {

    private int arr[] = {R.drawable.motor, R.drawable.car, R.drawable.box, R.drawable.burger};
    private String brr[] = {"Motor", "Mobil", "Send", "Food"};
    private ArrayList<DashboardGridItemsModal> gridItemList = new ArrayList<DashboardGridItemsModal>();
    DashboardGridItemsModal itemsModal = new DashboardGridItemsModal();
    private RecyclerView recyclerView;
    private BannerLayout bannerLayout;
    private List<String> imageUrls;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        for (int i = 0; i <= 3; i++) {
            itemsModal = new DashboardGridItemsModal();
            itemsModal.setImageUrl(arr[i]);
            itemsModal.setItemText(brr[i]);
            gridItemList.add(itemsModal);


        }
        recyclerView = view.findViewById(R.id.rvxRecycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL));
        DashboardRecyclerAdapter adapter = new DashboardRecyclerAdapter(gridItemList, getContext(), new DashbordRecyclerItemclick() {
            @Override
            public void onItemClick(View v, int position) {
                if(position==0){
                    Intent intent=new Intent(getActivity(), MotorTripActivity.class);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(adapter);




        imageUrls = new ArrayList<String>();
        imageUrls.add("https://d13ezvd6yrslxm.cloudfront.net/wp/wp-content/images/ironman-spiderman-homecoming-poster-frontpage-700x354.jpg");
        imageUrls.add("https://d13ezvd6yrslxm.cloudfront.net/wp/wp-content/images/ironman-spiderman-homecoming-poster-frontpage-700x354.jpg");
        imageUrls.add("https://d13ezvd6yrslxm.cloudfront.net/wp/wp-content/images/ironman-spiderman-homecoming-poster-frontpage-700x354.jpg");
        imageUrls.add("https://d13ezvd6yrslxm.cloudfront.net/wp/wp-content/images/ironman-spiderman-homecoming-poster-frontpage-700x354.jpg");

        bannerLayout = view.findViewById(R.id.bannerLayout);
        bannerLayout.setImageLoader(new GlideImageLoader());
        bannerLayout.setViewUrls(imageUrls);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setUp(View view) {

    }

}
