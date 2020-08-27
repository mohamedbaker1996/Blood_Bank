package com.mohamed.bloodbank.view.fragment.splashCycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mohamed.bloodbank.R;
import com.mohamed.bloodbank.helper.HelperMethods;
import com.mohamed.bloodbank.view.fragment.BaseFragment;

import java.util.Objects;

public class SplashFragment extends BaseFragment {


    public SplashFragment() {
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

        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HelperMethods.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), R.id.splash_cycle_activity_frame, new SliderFragment());
               // HelperMethods.replaceFragment(getActivity().getSupportFragmentManager(), R.id.splash_cycle_activity_frame, new SliderFragment());

            }
        }, 3000);
        return view;
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }

}
