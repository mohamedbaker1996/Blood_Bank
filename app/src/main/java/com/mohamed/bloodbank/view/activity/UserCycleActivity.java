package com.mohamed.bloodbank.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.fragment.userCycle.LoginFragment;

public class UserCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);
        HelperMethods.replaceFragment(getSupportFragmentManager(),R.id.user_cycle_frame,new LoginFragment());

    }
}
