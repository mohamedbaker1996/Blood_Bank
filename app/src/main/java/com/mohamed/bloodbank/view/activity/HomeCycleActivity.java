package com.mohamed.bloodbank.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.fragment.homeCycle.DonationsListFragment;
import com.mohamed.bloodbank.view.fragment.homeCycle.EditProfileFragment;
import com.mohamed.bloodbank.view.fragment.homeCycle.HomeFragment;
import com.mohamed.bloodbank.view.fragment.homeCycle.MoreFragment;
import com.mohamed.bloodbank.view.fragment.homeCycle.NotificationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCycleActivity extends BaseActivity {

    @BindView(R.id.frame_home_cycle)
    FrameLayout frameHomeCycle;
    @BindView(R.id.bottom_nav_home)
    BottomNavigationView bottomNavHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);
        ButterKnife.bind(this);
        HelperMethods.replaceFragment(getSupportFragmentManager(), R.id.frame_home_cycle, new HomeFragment());
        bottomNavHome.setOnNavigationItemSelectedListener(navListener);

    }


//    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_edit_profile:
                    selectedFragment = new EditProfileFragment();
                    break;
                case R.id.nav_notification:
                    selectedFragment = new NotificationFragment();
                    break;
                case R.id.nav_more:
                    selectedFragment = new MoreFragment();
                    break;
            }
            HelperMethods.replaceFragment(getSupportFragmentManager(), R.id.frame_home_cycle, selectedFragment);
            return true;
        } };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
