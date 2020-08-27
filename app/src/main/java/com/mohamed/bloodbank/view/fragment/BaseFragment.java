package com.mohamed.bloodbank.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.view.activity.BaseActivity;

public class BaseFragment extends Fragment {
 public BaseActivity baseActivity;
    public void setUpActivity() {
        baseActivity = (BaseActivity) getActivity();

        baseActivity.baseFragment = this;
    }
 public void onBack(){
     baseActivity.superBackPressed();
    }
}