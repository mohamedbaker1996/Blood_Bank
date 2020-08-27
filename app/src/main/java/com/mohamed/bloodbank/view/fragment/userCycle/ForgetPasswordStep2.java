package com.mohamed.bloodbank.view.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

public class ForgetPasswordStep2 extends BaseFragment {

    public static String Phone;

    public ForgetPasswordStep2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setUpActivity();
        return inflater.inflate(R.layout.fragment_forget_password_step_2, container, false);

    }
    @Override
    public void onBack(){
        super.onBack();
    }

}
