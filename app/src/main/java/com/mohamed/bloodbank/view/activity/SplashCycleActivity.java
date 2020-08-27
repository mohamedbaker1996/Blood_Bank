package com.mohamed.bloodbank.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.fragment.splashCycle.SliderFragment;
import com.mohamed.bloodbank.view.fragment.splashCycle.SplashFragment;

public class SplashCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);
        HelperMethods.replaceFragment(getSupportFragmentManager(), R.id.splash_cycle_activity_frame, new SplashFragment());

    }
}
