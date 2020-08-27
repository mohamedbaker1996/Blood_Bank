package com.mohamed.bloodbank.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {

   public BaseFragment baseFragment;


    public void superBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
      baseFragment.onBack();
    }
}
