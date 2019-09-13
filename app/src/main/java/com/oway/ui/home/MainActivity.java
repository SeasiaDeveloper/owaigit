package com.oway.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.oway.R;
import com.oway.base.BaseActivity;
import com.oway.model.response.LoginResponse;
import com.oway.ui.home.Profile.ProfileFragment;
import com.oway.ui.home.RequisiteAndCertain.RequisiteAndCertainFragment;
import com.oway.ui.home.dashboard.DashBoardFragment;
import com.oway.ui.home.dashboard.PageAdapter;
import com.oway.ui.login.LoginActivityPresenter;
import com.oway.ui.login.LoginActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, LoginActivityView, View.OnClickListener {

    @Inject
    LoginActivityPresenter<LoginActivityView> loginActivityPresenter;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.pager)
    ViewPager viewPager;


  /*  @BindView(R.id.tvDashBoard)
    TextView tvDashBoard;

    @BindView(R.id.tvUserProfile)
    TextView tvUserProfile;

    @BindView(R.id.tvPrequisties)
    TextView tvPrequisties;

    @BindView(R.id.tvPrivacyPolicy)
    TextView tvPrivacyPolicy;

    @BindView(R.id.tvFaq)
    TextView tvFaq;

    @BindView(R.id.tvAppRate)
    TextView tvAppRate;

    @BindView(R.id.tvLogout)
    TextView tvLogout;*/

    private int lastClickedNavId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        loginActivityPresenter.onAttach(MainActivity.this);
        setUp();

    }

    public static void start(BaseActivity baseActivity) {
        Intent intent = new Intent(baseActivity, MainActivity.class);
        baseActivity.startActivity(intent);
    }

    @Override
    protected void setUp() {
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int id = R.id.navigation_home;
                switch (i) {
                    case 0:
                        id = R.id.navigation_home;
                        break;
                    case 1:
                        id = R.id.navigation_activity;
                        break;
                    case 2:
                        id = R.id.navigation_payment;
                        break;
                    case 3:
                        id = R.id.navigation_account;
                        break;

                }
                navigation.setSelectedItemId(id);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        navigation.setOnNavigationItemSelectedListener(menuItem -> {
            int pos = 0;

            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    pos = 0;
                    break;
                case R.id.navigation_activity:
                    pos = 1;
                    break;
                case R.id.navigation_payment:
                    pos = 2;
                    break;
                case R.id.navigation_account:
                    pos = 3;
                    break;

            }
            viewPager.setCurrentItem(pos, true);
            return true;
        });

       /* tvUserProfile.setOnClickListener(this);
        tvPrequisties.setOnClickListener(this);
        tvPrivacyPolicy.setOnClickListener(this);
        tvFaq.setOnClickListener(this);
        tvAppRate.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
        tvDashBoard.setOnClickListener(this);*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //changeToolbarFont(toolbar);
       /* final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (lastClickedNavId != 0) {
                    onNavItemClicked(lastClickedNavId);
                    lastClickedNavId = 0;
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                KeyboardUtils.hideKeyboard(MainActivity.this);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        toggle.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_hamburger));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
        //setFragment(new DashBoardFragment(), DashBoardFragment.class.getSimpleName());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        showMessage(R.string.coming_soon);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSuccess(LoginResponse status) {

    }

    @Override
    public void onFailure(String msg) {

    }

    public void setFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()/*.
                setCustomAnimations(R.anim.fadein,R.anim.fadeout)*/
                .replace(R.id.frameContainer, fragment, tag)
                .commit();
    }

   /* public void changeToolbarFont(Toolbar toolbar) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                if (tv.getText().equals(toolbar.getTitle())) {
                    applyFont(tv, this);
                    break;
                }
            }
        }
    }*/

    public void onNavItemClicked(Integer id) {
        switch (id) {
            case R.id.tvDashBoard:
                setFragment(new DashBoardFragment(), DashBoardFragment.class.getSimpleName());
                break;
            case R.id.tvUserProfile:
                setFragment(new ProfileFragment(), ProfileFragment.class.getSimpleName());
                break;
            case R.id.tvPrequisties:
                setFragment(new RequisiteAndCertainFragment(), RequisiteAndCertainFragment.class.getSimpleName());
                break;
            case R.id.tvPrivacyPolicy:
                break;
            case R.id.tvFaq:
                break;
            case R.id.tvAppRate:
                break;
            case R.id.tvLogout:
                showLogoutAlert();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        lastClickedNavId = view.getId();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                @SuppressLint("WrongViewCast") DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        }, 200);
    }
}
